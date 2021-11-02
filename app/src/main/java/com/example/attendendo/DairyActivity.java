package com.example.attendendo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class DairyActivity extends AppCompatActivity {

    List<Entry> entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dairy);
        Toolbar toolbar = findViewById(R.id.dairy_Toolbar);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_Entries);
        CalendarView calendarView = findViewById(R.id.calenderView);
        DBhelper dBhelper = new DBhelper(this);
        entries = dBhelper.getAllEntryList();
        DairyAdapter adapter = new DairyAdapter(entries);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        // Managing the action when tab esected

        TabLayout tabLayout = findViewById(R.id.tabLayout_Entries);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                switch (position){
                    case 0:
                        calendarView.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        calendarView.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        TextView textView = findViewById(R.id.data_text);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month = month+1;
                textView.setText(""+dayOfMonth+"."+month+"."+year);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAction_NewEntry:
                Intent intent = new Intent(DairyActivity.this,DairyCreateEntry.class);
                startActivity(intent);
            return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}