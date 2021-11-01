package com.example.attendendo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    // Creating a BiometricAct obj to implement functionality of BIOMETRICS
    BiometricAct bio;
    // Creation a button obj
    Button button_Login;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Attaching the button variable to the Button View
        button_Login = findViewById(R.id.Buton_login);

        button_Login.setOnClickListener(view -> bio.bioMetricPrompt(MainActivity.this));

        bio = new BiometricAct();
        //Checking Compatibility
        if (bio.checkCompatibility(this)==true){

        }
        else{
            // Creating a Dialog if FingerPrint Sensor is not found
            alertDialog();
        }



    }
    // Declaration
    private void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Fingerprint Diary");
        builder.setIcon(R.mipmap.ic_launcher);
        // didn't got this
        builder.setMessage("Your device doesnot Support Finger Print").setCancelable(false)
                .setPositiveButton("Exit",(dialog,id)-> finish());
                AlertDialog alert = builder.create();
                alert.show();
    }
}