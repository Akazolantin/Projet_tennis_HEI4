package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;

public class Pagecalendrier extends Activity {
    private static final String TAG="calendarActvity";
    private CalendarView calendrier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagecalendrier);
        calendrier=(CalendarView) findViewById(R.id.calendarView);
        calendrier.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange( CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month+1) + "/" + year ;
                Log.d(TAG, "onSelectedDayChange : dd/mm/yyyy: " + date);
                Intent intent1=new Intent(Pagecalendrier.this,Calendrier.class);
                intent1.putExtra("date",date);
                startActivity(intent1);



            }
        });



    }
}