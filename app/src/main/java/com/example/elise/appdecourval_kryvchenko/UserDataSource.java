package com.example.elise.appdecourval_kryvchenko;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elise on 14/12/2015.
 */
public class UserDataSource {


        // Champs de la base de données
        private SQLiteDatabase database;
        private MySQLiteHelperUser dbHelper;
        private String[] allColumns = { MySQLiteHelperUser.COLUMN_ID, MySQLiteHelperUser.COLUMN_SEXE,
                MySQLiteHelperUser.COLUMN_AGE,  MySQLiteHelperUser.COLUMN_SPORT};

        public UserDataSource(Context context) {
            dbHelper = new MySQLiteHelperUser(context);
        }

        public void open() throws SQLException {
            database = dbHelper.getWritableDatabase();
        }

        public void close() {
            dbHelper.close();
        }

        public User createUser(int sexe, int age, int sport) {
            ContentValues values = new ContentValues();
            values.put( MySQLiteHelperUser.COLUMN_SEXE,sexe );
            values.put(MySQLiteHelperUser.COLUMN_AGE,age );
            values.put(MySQLiteHelperUser.COLUMN_SPORT, sport);

            int insertId = 1 ; // database.insert(MySQLiteHelperUser.TABLE_USER, null,values);
            Cursor cursor = database.query(MySQLiteHelperUser.TABLE_USER,
                    allColumns, MySQLiteHelperUser.COLUMN_ID + " = " + insertId, null,
                    null, null, null);
            cursor.moveToFirst();
            User newUser = cursorToUser(cursor);
            cursor.close();
            return newUser;
        }



        public boolean updateUser (int id, int sexe, int age, int sport)
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put("_id", id);
            contentValues.put("sexe", sexe);
            contentValues.put("age", age);
            contentValues.put("sport", sport);
            return true;
        }

       /* public void deleteUser(User user) {
            long id = user.getId();
            System.out.println("User deleted with id: " + id);
            database.delete(MySQLiteHelperUser.TABLE_USER, MySQLiteHelperUser.COLUMN_ID
                    + " = " + id, null);
        }*/

        public List<User> getAllUsers() {
            List<User> users = new ArrayList<User>();

            Cursor cursor = database.query(MySQLiteHelperUser.TABLE_USER,
                    allColumns, null, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                User user = cursorToUser(cursor);
                users.add(user);
                cursor.moveToNext();
            }
            // assurez-vous de la fermeture du curseur
            cursor.close();
            return users;
        }

        private User cursorToUser(Cursor cursor) {
            User user = new User();
            //user.setId(cursor.getInt(0));
            user.setSexe(cursor.getInt(1));
            user.setAge(cursor.getInt(2));
            user.setSport(cursor.getInt(3));
            return user;
        }

    public Cursor getSexe(int id){
        Cursor res =  database.rawQuery( "select sexe from contacts where id="+id+"", null );
        return res;
    }

    public Cursor getData(int id){
        Cursor res =  database.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }
}
