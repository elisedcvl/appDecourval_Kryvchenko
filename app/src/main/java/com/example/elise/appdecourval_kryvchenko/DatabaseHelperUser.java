package com.example.elise.appdecourval_kryvchenko;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class DatabaseHelperUser extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="User.db";
    public static final String TABLE_NAME="user_table";
    public static final String col_1="ID";
    public static final String col_2="SEXE";
    public static final String col_3="AGE";
    public static final String col_4="SPORT";


    public DatabaseHelperUser(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //------Use for database create----------//
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER ,SEXE INTEGER, AGE INTEGER, SPORT INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public  boolean insertData(int id, int sexe, int age, int sport){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col_1,id);
        contentValues.put(col_2,sexe);
        contentValues.put(col_3,age);
        contentValues.put(col_4,sport);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if (result== -1)
            return false;
        else
            return true;
    }


    public boolean deleteAllUsers() {
        SQLiteDatabase db=this.getWritableDatabase();
        long doneDelete = db.delete(TABLE_NAME, null , null);
        if (doneDelete== -1)
            return false;
        else
            return true;

    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;

    }

}
