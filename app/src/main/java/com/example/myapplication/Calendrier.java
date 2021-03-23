package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Calendrier extends Activity {
    private static final String TAG="Calendrier";
    private TextView textedate;
    private Button Boutondate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendrier);
        textedate=(TextView) findViewById(R.id.textView);
        Boutondate=(Button) findViewById(R.id.buttondate);
        Intent incomingIntent=getIntent();
        String date =incomingIntent.getStringExtra("date");
        textedate.setText(date);
        Boutondate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Calendrier.this,Pagecalendrier.class);
                startActivity(intent);
                finish();

            }
        });

    }
}