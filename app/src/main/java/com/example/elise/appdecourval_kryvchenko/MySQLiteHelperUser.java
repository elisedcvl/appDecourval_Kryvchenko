package com.example.elise.appdecourval_kryvchenko;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Elise on 14/12/2015.
 */
public class MySQLiteHelperUser extends SQLiteOpenHelper {

        public static final String TABLE_USER = "User";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_SEXE= "sexe";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_SPORT = "sport";

        private static final String DATABASE_NAME = "user.db";
        private static final int DATABASE_VERSION = 1;

        // Commande sql pour la création de la base de données
        private static final String DATABASE_CREATE = "create table "
                + TABLE_USER + "(" + COLUMN_ID
                + " integer primary key, "  + COLUMN_SEXE
                + " integer, " + COLUMN_AGE
                + " integer not null," + COLUMN_SPORT + " integer not null); " ;

        public MySQLiteHelperUser(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }



    @Override
        public void onCreate(SQLiteDatabase database) {
            database.execSQL(DATABASE_CREATE);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(MySQLiteHelperUser.class.getName(),
                    "Upgrading database from version " + oldVersion + " to "
                            + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
            onCreate(db);
        }



}
