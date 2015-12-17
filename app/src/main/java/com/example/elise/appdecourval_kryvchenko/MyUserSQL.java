package com.example.elise.appdecourval_kryvchenko;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Elise on 16/12/2015.
 */
public class MyUserSQL extends SQLiteOpenHelper {

    public static final String TABLE_USER = "User";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SEXE= "sexe";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_SPORT = "sport";

    private static final String CREATE_BDD = "create table "
            + TABLE_USER + "(" + COLUMN_ID
            + " integer primary key, "  + COLUMN_SEXE
            + " integer, " + COLUMN_AGE
            + " integer not null," + COLUMN_SPORT + " integer not null); " ;


      public MyUserSQL(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on créé la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut fait ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE " + TABLE_USER + ";");
        onCreate(db);
    }

}