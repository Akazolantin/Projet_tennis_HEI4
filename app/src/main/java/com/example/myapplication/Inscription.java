package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inscription extends Activity {
    private Button boutonvalidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        this.boutonvalidation=(Button) findViewById(R.id.Boutoninscription);
        boutonvalidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Acitivity2=new Intent(getApplicationContext(),Maildeconfirmation.class);
                startActivity(Acitivity2);
                finish();
            }
    });
}
}