package com.example.attendendo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class DairyCreateEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dairy_createentry);
        // Creating an instance of  toolbarv with the view id
        Toolbar tb = (Toolbar) findViewById(R.id.tb_CreateDairy);

        //Setting Navigation on The toolbar
         tb.setNavigationOnClickListener(v -> exit());

        Button button =  (Button) findViewById(R.id.dairyInputEntry);
        button.setOnClickListener(v -> {
            AlertDialog dialog =  creatEntryDialog();
            dialog.show();
        });
    }
    //Declaration creatEntryDialog
    private AlertDialog creatEntryDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(DairyCreateEntry.this);
        builder.setMessage("Save Entry");
        builder.setPositiveButton("yes",(dialog, which) -> {
            // Taking the title input
            TextInputEditText inputTitle = (TextInputEditText) findViewById(R.id.Input_title);
            String title = inputTitle.getText().toString();

            // Taking a text input
            TextInputEditText inputText = (TextInputEditText) findViewById(R.id.Input_Text);
            String text = inputText.getText().toString();

            Entry entry = Entry.createDairyEntry(DairyCreateEntry.this,title,text);

            DBhelper dbHelper = new DBhelper(DairyCreateEntry.this);
            dbHelper.addEntries(entry);
            exit();

        });

        builder.setNegativeButton("Cancle",(((dialog, which) ->{

        })));
        AlertDialog dialog = builder.create();
        return dialog;


    }

    public void exit(){
        Intent intent = new Intent(DairyCreateEntry.this,DairyActivity.class);
        startActivity(intent);
        DairyCreateEntry.this.finish();

    }
}