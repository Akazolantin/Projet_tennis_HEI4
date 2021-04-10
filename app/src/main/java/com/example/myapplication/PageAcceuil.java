package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
public class PageAcceuil extends AppCompatActivity {
    private Button boutton2;
    private Button boutton3;
    private Button boutton4;
    private Button boutton5;
    private Button boutton6;
    private Button boutton7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_acceuil);

        this.boutton2 = (Button) findViewById(R.id.button2);
        boutton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(),Statistique.class );
                startActivity(otherActivity);
            }
        });

        this.boutton3 = (Button) findViewById(R.id.button3);
        boutton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(),Pagecalendrier.class );
                startActivity(otherActivity);
            }
        });
        this.boutton4 = (Button) findViewById(R.id.button4);
        boutton4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(),Information.class );
                startActivity(otherActivity);

            }
        });
        this.boutton5 = (Button) findViewById(R.id.button5);
        boutton5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(),Codeportail.class );
                startActivity(otherActivity);

            }
        });
        this.boutton6 = (Button) findViewById(R.id.button6);
        boutton6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(),MainActivity.class );
                startActivity(otherActivity);

            }
        });
        this.boutton7 = (Button) findViewById(R.id.button7);
        boutton7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent otherActivity = new Intent(getApplicationContext(),EnregisterMatch.class );
                startActivity(otherActivity);

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