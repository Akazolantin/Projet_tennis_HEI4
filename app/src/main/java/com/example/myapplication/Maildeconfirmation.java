package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Maildeconfirmation extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maildeconfirmation);
        textView = findViewById(R.id.text_view);
        long duration = TimeUnit.MINUTES.toMillis(1);
        new CountDownTimer(duration, 1000) {

            @Override
            public void onTick(long l) {
                String sduration=String.format(Locale.FRANCE,"%02d:%02d",TimeUnit.MILLISECONDS.toMinutes(l),TimeUnit.MILLISECONDS.toSeconds(l)-
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));
                Toast.makeText(getApplicationContext(),"Message en cours d'envoie",Toast.LENGTH_LONG).show();
                textView.setText(sduration);
            }

            @Override
            public void onFinish() {
                textView.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"Message envoyé",Toast.LENGTH_LONG).show();

            }
        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(SIngleton.getId() == 0) {
            getMenuInflater().inflate(R.menu.not_connected, menu);
        }else{
            if(SIngleton.isAdmin()){
                getMenuInflater().inflate(R.menu.admin, menu);
            }else{
                getMenuInflater().inflate(R.menu.connected, menu);
            }
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_admin:
                Intent Acitivity9 = new Intent(getApplicationContext(), Pageadministrateur.class);
                startActivity(Acitivity9);
                return true;
            case R.id.menu_inscription:
                Intent Acitivity8 = new Intent(getApplicationContext(), Inscription.class);
                startActivity(Acitivity8);
                return true;
            case R.id.connexion:
                Intent Acitivity7 = new Intent(getApplicationContext(), Pageconnexion.class);
                startActivity(Acitivity7);
                return true;
            case R.id.menu_calendrier:
                Intent Acitivity6 = new Intent(getApplicationContext(), Pagecalendrier.class);
                startActivity(Acitivity6);
                return true;
            case R.id.menu_accueil:
                Intent Acitivity5= new Intent(getApplicationContext(), MainActivity.class);
                if(SIngleton.getId() != 0) {
                    Acitivity5 = new Intent(getApplicationContext(), PageAcceuil.class);}
                startActivity(Acitivity5);
                return true;
            case R.id.menu_match:
                Intent Acitivity4 = new Intent(getApplicationContext(), EnregisterMatch.class);
                startActivity(Acitivity4);
                return true;
            case R.id.menu_deconnexion:
                SIngleton.setAdmin(false);
                SIngleton.setId(0);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

