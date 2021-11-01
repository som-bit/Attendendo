package com.example.attendendo;

import android.content.Context;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Entry {


    int id;
    String date;
    String title;
    String text;


    public Entry() {
    }

    public Entry(String date, String title, String text) {
        this.date = date;
        this.title = title;
        this.text = text;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static Entry createDairyEntry(Context context, String title, String text){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context);
        String finalDate = dateFormat.format(date);
        Entry newEntry = new Entry(finalDate,text,title);
        return newEntry;






    }
}
