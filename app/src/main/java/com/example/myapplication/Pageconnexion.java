package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Pageconnexion extends Activity {
    private Button bouton;
    private static String[] identifiants= new String[]{
            "Bob l'éponge","Superman","Trump","IronMan","Un ours","Mario","Cléopatre","Batman","Un pirate","Un chat",
            "Mickey","Asterix","Une fée","Un télétubbie","Un T-rex","Un loup","Napoléon","Pac-Man","Pikachu","Un monstre"
    };
    private static String[] mdps= new String[]{
            "Bob l'éponge","Superman","Trump","IronMan","Un ours","Mario","Cléopatre","Batman","Un pirate","Un chat",
            "Mickey","Asterix","Une fée","Un télétubbie","Un T-rex","Un loup","Napoléon","Pac-Man","Pikachu","Un monstre"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pageconnexion);
        EditText identView = findViewById(R.id.username);
        this.bouton=(Button) findViewById(R.id.button);
        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ident = identView.getText().toString();
                int y;
                for (int i=0;i<identifiants.length;i++){
                    if(ident==identifiants[i]){
                        y=i;
                        break;
                    }
                }
                Intent Acitivity2=new Intent(getApplicationContext(),Calendrier.class);
                startActivity(Acitivity2);
                finish();
            }
        });


    }
}
