package com.example.myapplication;

import java.util.Date;

public class Hour {
    private int hour;
    private int date;
    private int color = 0xff00FF00;
    public Hour(int hour, int date){
        this.hour=hour;
        this.date=date;
    }

    public int getDate() {
        return date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
