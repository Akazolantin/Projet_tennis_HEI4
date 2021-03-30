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
}



