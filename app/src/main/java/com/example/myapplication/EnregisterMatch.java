package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class EnregisterMatch extends AppCompatActivity {

    Button validerButton;
    Button annulerButton;
    Button date;
    Spinner adversaireSpinner;
    EditText scoreMatch;
    Calendar calendar;
    DatePickerDialog datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregister_match);
        validerButton=(Button) findViewById(R.id.match_button_valider);
        annulerButton=(Button) findViewById(R.id.match_button_annuler);
        date = (Button) findViewById(R.id.match_date);
        adversaireSpinner=(Spinner) findViewById(R.id.match_adversaire);
        scoreMatch=(EditText) findViewById(R.id.match_score);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar=Calendar.getInstance();
                int lday=calendar.get(Calendar.DAY_OF_MONTH);
                int lmonth = calendar.get(Calendar.MONTH);
                int lyear= calendar.get(Calendar.YEAR);

                datePicker= new DatePickerDialog(EnregisterMatch.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(dayOfMonth+"/"+month+"/"+year);
                    }
                },lday, lmonth, lyear);
            }
        });


        validerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        annulerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Annulation = new Intent(getApplicationContext(), PageAcceuil.class);
                startActivity(Annulation);
            }
        });
    }


}