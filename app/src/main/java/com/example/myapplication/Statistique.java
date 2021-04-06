package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Statistique extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistique);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.statistique, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_deconnexion:
                Intent Acitivity1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Acitivity1);
                return true;
            case R.id.menu_portail:
                Intent Acitivity5 = new Intent(getApplicationContext(), Codeportail.class);
                startActivity(Acitivity5);
                return true;
            case R.id.menu_enregister:
                Intent Acitivity6 = new Intent(getApplicationContext(), Enregistrementcarte.class);
                startActivity(Acitivity6);
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }
    }
}