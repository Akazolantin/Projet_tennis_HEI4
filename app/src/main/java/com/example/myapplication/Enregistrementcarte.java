package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Enregistrementcarte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrementcarte);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.enregistrement_carte, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_deconnexion:
                Intent Acitivity1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Acitivity1);
                return true;
            case R.id.menu_informations:
                Intent Acitivity2 = new Intent(getApplicationContext(), Information.class);
                startActivity(Acitivity2);
                return true;
            case R.id.menu_statistiques:
                Intent Acitivity3 = new Intent(getApplicationContext(), Statistique.class);
                startActivity(Acitivity3);
                return true;
            case R.id.menu_portail:
                Intent Acitivity4 = new Intent(getApplicationContext(),Codeportail.class);
                startActivity(Acitivity4);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
    }