package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Calendrier extends AppCompatActivity{
    private static final String TAG = "Calendrier";
    private TextView textedate;
    private Button Boutondate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendrier);
        textedate = (TextView) findViewById(R.id.textView);
        Boutondate = (Button) findViewById(R.id.buttondate);
        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        textedate.setText(date);
        Boutondate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Calendrier.this, Pagecalendrier.class);
                startActivity(intent);
                finish();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calendrier, menu);
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
            default:
                return super.onOptionsItemSelected(item);


        }
    }
}