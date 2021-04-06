package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Pagecalendrier extends AppCompatActivity {

    private static final String TAG = "calendarActvity";
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagecalendrier);
        calendrier = (CalendarView) findViewById(R.id.calendarView);
        calendrier.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month + 1) + "/" + year;
                Log.d(TAG, "onSelectedDayChange : dd/mm/yyyy: " + date);
                Intent intent1 = new Intent(Pagecalendrier.this, Calendrier.class);
                intent1.putExtra("date", date);
                startActivity(intent1);
            }
        });

        private void initWidgets ()
        {
            calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
            monthYearText = findViewById(R.id.monthYear);
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        private void setMonthView ()
        {
            monthYearText.setText(monthYearFromDate(selectedDate));
            ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

            CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this::onItemClick);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
            calendarRecyclerView.setLayoutManager(layoutManager);
            calendarRecyclerView.setAdapter(calendarAdapter);
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        private ArrayList<String> daysInMonthArray (LocalDate date)
        {
            ArrayList<String> daysInMonthArray = new ArrayList<>();
            YearMonth yearMonth = YearMonth.from(date);

            int daysInMonth = yearMonth.lengthOfMonth();

            LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
            int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

            for (int i = 1; i <= 42; i++) {
                if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                    daysInMonthArray.add("");
                } else {
                    daysInMonthArray.add(String.valueOf(i - dayOfWeek));
                }
            }
            return daysInMonthArray;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        private String monthYearFromDate (LocalDate date)
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
            return date.format(formatter);
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void previousMonthAction (View view)
        {
            selectedDate = selectedDate.minusMonths(1);
            setMonthView();
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void nextMonthAction (View view)
        {
            selectedDate = selectedDate.plusMonths(1);
            setMonthView();
        }

        @RequiresApi(api = Build.VERSION_CODES.O)

        public void onItemClick ( int position, String dayText)
        {

            if (!dayText.equals("")) {
                String message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate);
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Pagecalendrier.this, Calendrier.class);
                startActivity(intent);
            }
        }

    }
}