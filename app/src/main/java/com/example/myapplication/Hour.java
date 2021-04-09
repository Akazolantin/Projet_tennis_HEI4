package com.example.myapplication;

import java.util.Date;

public class Hour {
    private int hour;
    private int date;
    private boolean participation = false;
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

    public boolean isParticipation() {
        return participation;
    }

    public void setParticipation(boolean participation) {
        this.participation = participation;
    }
}
