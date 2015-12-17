package com.example.elise.appdecourval_kryvchenko;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elise on 16/12/2015.
 */
public class UserBDD {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "user.db";

    private static final String TABLE_USER = "table_users";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_SEXE= "Sexe";
    private static final int NUM_COL_SEXE = 1;
    private static final String COL_AGE = "Age";
    private static final int NUM_COL_AGE= 2;
    private static final String COL_SPORT = "Sport";
    private static final int NUM_COL_SPORT = 3;

    private SQLiteDatabase bdd;

    private MyUserSQL maBaseSQLite;

    private String[] allColumns = { maBaseSQLite.COLUMN_ID, maBaseSQLite.COLUMN_SEXE,
            maBaseSQLite.COLUMN_AGE,  maBaseSQLite.COLUMN_SPORT};


    public UserBDD(Context context){
        //On créer la BDD et sa table
        maBaseSQLite = new MyUserSQL(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertUser(User user){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_ID, user.getId());
        values.put(COL_SEXE, user.getSexe());
        values.put(COL_AGE, user.getAge());
        values.put(COL_SPORT, user.getSport());

        /*values.put( maBaseSQLite.COLUMN_ID,user.getId() );
        values.put( maBaseSQLite.COLUMN_SEXE,user.getSexe() );
        values.put(maBaseSQLite.COLUMN_AGE,user.getAge() );
        values.put(maBaseSQLite.COLUMN_SPORT, user.getSport());*/

        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_USER, null, values);
    }

    public User createUser(User user) {
        ContentValues values = new ContentValues();
        values.put( maBaseSQLite.COLUMN_ID,user.getId() );
        values.put( maBaseSQLite.COLUMN_SEXE,user.getSexe() );
        values.put(maBaseSQLite.COLUMN_AGE,user.getAge() );
        values.put(maBaseSQLite.COLUMN_SPORT, user.getSport());



        int insertId = 1 ; // database.insert(MySQLiteHelperUser.TABLE_USER, null,values);
        Cursor cursor = bdd.query(maBaseSQLite.TABLE_USER,
                allColumns, maBaseSQLite.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        User newUser = cursorToUser(cursor);
        cursor.close();
        return newUser;
    }

    public int updateUser(String id, User user){
        //La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simple préciser quelle livre on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_ID, user.getId());
        values.put(COL_SEXE, user.getSexe());
        values.put(COL_AGE, user.getAge());
        values.put(COL_SPORT, user.getSport());
        return bdd.update(TABLE_USER, values, COL_ID + " = " + id, null);
    }

    public int removeUSERWithID(String id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_USER, COL_ID + " = " + id, null);
    }

    public User getUserWithId(String id){
        //Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TABLE_USER, allColumns, COL_ID + " LIKE \"" + id +"\"", null, null, null, null);
        return cursorToUser(c);
    }

    //Cette méthode permet de convertir un cursor en un livre
    private User cursorToUser(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un livre
        User user = new User() ;
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        user.setId(c.getString(NUM_COL_ID));
        user.setSexe(c.getInt(NUM_COL_SEXE));
        user.setAge(c.getInt(NUM_COL_AGE));
        user.setSport(c.getInt(NUM_COL_SPORT));
        //On ferme le cursor
        c.close();

        //On retourne le livre
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        Cursor cursor = bdd.query(maBaseSQLite.TABLE_USER,allColumns, null, null, null, null, null);
                /*bdd.rawQuery("select * from TABLE_USER ", null) */

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
}
