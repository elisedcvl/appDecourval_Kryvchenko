package com.example.elise.appdecourval_kryvchenko;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Elise on 14/12/2015.
 */
public class User {
    private String _id;
    private int sexe;
    private int age;
    private int sport;

    public User(){}
    public User(String _id, int sexe, int age, int sport) {
        this._id= _id;
        this.sexe=sexe;
        this.age=age;
        this.sport=sport;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public int getSexe() {
        return sexe;
    }

    public void setSexe(int sexe) {
        this.sexe = sexe;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSport() {
        return sport;
    }

    public void setSport(int sport) {
        this.sport = sport;
    }







}