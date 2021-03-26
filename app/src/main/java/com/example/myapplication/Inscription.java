package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Inscription extends AppCompatActivity {
    private Button boutonvalidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        this.boutonvalidation = (Button) findViewById(R.id.Boutoninscription);
        boutonvalidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Acitivity2 = new Intent(getApplicationContext(), Maildeconfirmation.class);
                startActivity(Acitivity2);
                finish();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_inscription, menu);
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








