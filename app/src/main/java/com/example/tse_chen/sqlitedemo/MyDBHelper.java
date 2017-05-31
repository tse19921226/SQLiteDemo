package com.example.tse_chen.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tse_Chen on 2017/5/31.
 */

public class MyDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Demo.db";
    public static final int VERSION = 1;
    public static final String Table_Name = "Test";
    //欄位編號
    public static final String Key_ID = "_id";

    //欄位
    public static final String Key_Name = "Name";
    public static final String Key_Age = "Age";
    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        final String Create_Table = "CREATE TABLE " + Table_Name + "(" +
                Key_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Key_Name + " TEXT, " +
                Key_Age + " TEXT" + ")";
        db.execSQL(Create_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insert(String Name, Integer Age){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Key_Name,Name);
        cv.put(Key_Age,Age);
        //database.update(Table_Name,cv,Key_ID,null);
        return database.insert(Table_Name,null,cv);
    }

    public Cursor select()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("Test", null, null, null, null, null, null);
        return cursor;
    }

    public void delete(int id)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        String where = Key_ID + " = " + Integer.toString(id) ;
        database.delete("sqldb", where, null);
    }

    public void deleteAll()
    {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("DELETE FROM " + " sqldb" );
    }
}
