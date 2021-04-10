package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    private Button bouton1;
    private Button bouton2;

///////////////////////////////////////////////////////
    private boolean back_answer = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            boolean debugState = false;
            if (debugState) {
                Toast.makeText(this, "BACK key press", Toast.LENGTH_SHORT).show();
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Connectez-vous pour accéder à votre espace")
                    .setCancelable(false)
                    .setPositiveButton("retour", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            back_answer = true;
                        }
                    });

            AlertDialog alert = builder.create();
            alert.show();
        }


        return back_answer;
    }

    ///////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.bouton1 = (Button) findViewById(R.id.Monbuton);
        bouton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherAcitivity = new Intent(getApplicationContext(), Pageconnexion.class);
                startActivity(otherAcitivity);
                finish();
            }
        });
        this.bouton2 = (Button) findViewById(R.id.Boutoninscription);
        bouton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherAcitivity = new Intent(getApplicationContext(), Inscription.class);
                startActivity(otherAcitivity);
                finish();
            }
        });


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



