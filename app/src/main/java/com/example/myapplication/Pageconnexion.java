package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Pageconnexion extends AppCompatActivity {
    private Button bouton;
    private static String[] identifiants = new String[]{
            "Bob l'éponge", "Superman", "Trump", "IronMan", "Un ours", "Mario", "Cléopatre", "Batman", "Un pirate", "Un chat",
            "Mickey", "Asterix", "Une fée", "Un télétubbie", "Un T-rex", "Un loup", "Napoléon", "Pac-Man", "Pikachu", "Un monstre"
    };
    private static String[] mdps = new String[]{
            "Bob l'éponge", "Superman", "Trump", "IronMan", "Un ours", "Mario", "Cléopatre", "Batman", "Un pirate", "Un chat",
            "Mickey", "Asterix", "Une fée", "Un télétubbie", "Un T-rex", "Un loup", "Napoléon", "Pac-Man", "Pikachu", "Un monstre"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pageconnexion);
        EditText identView = findViewById(R.id.username);
        EditText identmotdepasse = findViewById(R.id.password);
        this.bouton = (Button) findViewById(R.id.button);
        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ident = identView.getText().toString();
                String ident1 = identmotdepasse.getText().toString();
                int i;
                int j = -1;
                for (i = 0; i < identifiants.length; i++) {
                    if (ident.equals(identifiants[i])) {
                        j = i;
                    }
                }
                if (j == -1) {
                    Toast.makeText(Pageconnexion.this, "Votre mot de passe ou username est incorrect", Toast.LENGTH_LONG).show();

                } else {

                    if (ident1.equals(mdps[j])) {
                        Intent Acitivity2 = new Intent(getApplicationContext(), PageAcceuil.class);
                        startActivity(Acitivity2);
                    } else {
                        Toast.makeText(Pageconnexion.this, "Votre mot de passe ou username est incorrect", Toast.LENGTH_LONG).show();

                    }


                }
            }


        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pageconnexion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_informations:
                Intent Acitivity2 = new Intent(getApplicationContext(), Information.class);
                startActivity(Acitivity2);
                return true;
            case R.id.menu_statistiques:
                Intent Acitivity3 = new Intent(getApplicationContext(), Statistique.class);
                startActivity(Acitivity3);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}


