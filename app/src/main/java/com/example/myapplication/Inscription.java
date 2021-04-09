package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Inscription extends AppCompatActivity {
    private Button buttonvalidation;
    private EditText text_nom;
    private EditText text_prenom;
    private EditText text_date;
    private EditText text_email;
    private EditText text_identifiant;
    private EditText text_mdp;
    private EditText text_verif_mdp;
    private boolean back_answer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        buttonvalidation = (Button) findViewById(R.id.inscription_button_inscription);
        text_nom=(EditText)findViewById(R.id.inscription_nom);
        text_prenom= (EditText) findViewById(R.id.inscription_prenom);
        text_date= (EditText) findViewById(R.id.inscription_date_naissance);
        text_email= (EditText) findViewById(R.id.inscription_email);
        text_identifiant= (EditText) findViewById(R.id.inscription_identifiant);
        text_mdp=(EditText) findViewById(R.id.inscription_mdp);
        text_verif_mdp= findViewById(R.id.inscription_verif_mdp);

        text_mdp.setTransformationMethod(PasswordTransformationMethod.getInstance());
        text_verif_mdp.setTransformationMethod(PasswordTransformationMethod.getInstance());

        text_mdp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });


        text_verif_mdp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(text_verif_mdp.equals(text_mdp)==false ){
                    Toast.makeText(Inscription.this, "Votre mot de passe ou identifiant est incorrect", Toast.LENGTH_LONG).show();

                }
            }
        });

        buttonvalidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Acitivity2 = new Intent(getApplicationContext(), Maildeconfirmation.class);
                startActivity(Acitivity2);

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








