package com.example.elise.appdecourval_kryvchenko;

import android.app.Activity;
import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.List;

public class TestBDUser extends ListActivity {

    private UserBDD userBdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_bduser);

        //Création d'une instance de ma classe LivresBDD
        userBdd = new UserBDD(this);

        //Création d'un livre
        //User user = new User("a", 1, 1, 1);

        //On ouvre la base de données pour écrire dedans
        userBdd.open();

        User user = new User("a", 1, 1, 1);
        //On insère le livre que l'on vient de créer
        userBdd.insertUser(user);
        userBdd.createUser(user);
        //On insère le livre que l'on vient de créer
        //userBdd.insertUser(user);

        //Pour vérifier que l'on a bien créé notre livre dans la BDD
        //on extrait le livre de la BDD grâce au titre du livre que l'on a créé précédemment
       //User userFromBdd = userBdd.getUserWithId(user.getId());

        List<User> values = userBdd.getAllUsers();

        ArrayAdapter<User> adapter = new ArrayAdapter<User>(this,android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

         /*//Si un livre est retourné (donc si le livre à bien été ajouté à la BDD)
        if (userFromBdd != null) {
            //On affiche les infos du livre dans un Toast
            Toast.makeText(this, userFromBdd.toString(), Toast.LENGTH_LONG).show();
            //On modifie le titre du livre
            userFromBdd.setId(1);
            //Puis on met à jour la BDD
            userBdd.updateUser(userFromBdd.getId(), userFromBdd);
        }

       //On extrait le livre de la BDD grâce au nouveau titre
        userFromBdd = userBdd.getUserWithId(2);
        //S'il existe un livre possédant ce titre dans la BDD
        if (userFromBdd != null) {
            //On affiche les nouvelle info du livre pour vérifié que le titre du livre a bien été mis à jour
            Toast.makeText(this, userFromBdd.toString(), Toast.LENGTH_LONG).show();
            //on supprime le livre de la BDD grâce à son ID
            userBdd.removeUSERWithID(userFromBdd.getId());
        }

        //On essait d'extraire de nouveau le livre de la BDD toujours grâce à son nouveau titre
        userFromBdd = userBdd.getUserWithId(1);
        //Si aucun livre n'est retourné
        if (userFromBdd == null) {
            //On affiche un message indiquant que le livre n'existe pas dans la BDD
            Toast.makeText(this, "Ce livre n'existe pas dans la BDD", Toast.LENGTH_LONG).show();
        }
        //Si le livre existe (mais normalement il ne devrait pas)
        else {
            //on affiche un message indiquant que le livre existe dans la BDD
            Toast.makeText(this, "Ce livre existe dans la BDD", Toast.LENGTH_LONG).show();
        }

        userBdd.close();*/
    }
}
