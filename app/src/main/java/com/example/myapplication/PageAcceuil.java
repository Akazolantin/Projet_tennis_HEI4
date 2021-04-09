package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class PageAcceuil extends AppCompatActivity {
    private Button boutton2;
    private Button boutton3;
    private Button boutton4;
    private Button boutton5;
    private Button boutton6;

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


    }
}