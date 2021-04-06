package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Information extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.information, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_deconnexion:
                Intent Acitivity1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Acitivity1);
                return true;
            case R.id.menu_statistiques:
                Intent Acitivity3 = new Intent(getApplicationContext(), Statistique.class);
                startActivity(Acitivity3);
                return true;
            case R.id.menu_portail:
                Intent Acitivity4 = new Intent(getApplicationContext(), Codeportail.class);
                startActivity(Acitivity4);
                return true;
            case R.id.menu_enregister:
                Intent Acitivity5 = new Intent(getApplicationContext(), Enregistrementcarte.class);
                startActivity(Acitivity5);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}



