package com.example.attendendo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBhelper extends SQLiteOpenHelper {
    // declering veriables and database attributes
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DiaryEntry.db";
    private static final String TABLE_ENRTIES = "entries";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DATE = "date";
    private static final String KEY_TEXT = "text";



    public  DBhelper (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }
    //Creating Entries, insurting, uptade, delete
    // concatenating the vlaues to creat a command

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_ENRTIES + "(" + KEY_ID + "INTEGER_PRIMARY_KEY_AUTOINCREMENT"
                                                        + KEY_DATE + "TEXT" + KEY_TITLE + "TEXT" + KEY_TEXT + "TEXT)";

    // command for droping table if exist
    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_ENRTIES;

    //Methode TO CREATE THE TABLE
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    //Methode to UPGRADE THE TABLE

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    //Methode to DELETE TABLE
    public void deleteTable (){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);

    }

    // PArameters for the command
    void addEntries(Entry diaryEntry){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_DATE,diaryEntry.getDate());
        values.put(KEY_TITLE,diaryEntry.getTitle());
        values.put(KEY_TEXT,diaryEntry.getText());
        db.insert(TABLE_ENRTIES,null,values);
        db.close();
    }
    public List<Entry> getAllEntryList () {
        List<Entry> entryList = new ArrayList<>();


        String selectQuary = " SELECT * FROM " + TABLE_ENRTIES + " ORDER BY " + KEY_ID + " DESC ";
               SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuary,null);
        if (cursor.moveToFirst()){
            do {
                Entry diaryEntry = new Entry();
                diaryEntry.setId(Integer.parseInt(cursor.getString(0)));
                diaryEntry.setDate(cursor.getString(1));
                diaryEntry.setTitle(cursor.getString(2));
                diaryEntry.setText(cursor.getString(3));
                entryList.add(diaryEntry);
            }while (cursor.moveToNext());


        }
        cursor.close();
        return entryList;
    }




}
