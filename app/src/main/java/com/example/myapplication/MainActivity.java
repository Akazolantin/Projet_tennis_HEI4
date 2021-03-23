package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends Activity {
    private Button bouton1;
    private Button bouton2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.bouton1=(Button) findViewById(R.id.Monbuton);
        bouton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherAcitivity=new Intent(getApplicationContext(),Pageconnexion.class);
                startActivity(otherAcitivity);
                finish();
            }
        });
        this.bouton2=(Button) findViewById(R.id.Boutoninscription);
        bouton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherAcitivity=new Intent(getApplicationContext(),Inscription.class);
                startActivity(otherAcitivity);
                finish();
            }
        });


    }
}