package com.example.myapplication;

import java.util.Date;

public class Hour {
    private int hour;
    private int date;
    private int terrain;
    private boolean participation = false;
    public Hour(int hour, int date,int terrain){
        this.hour=hour;
        this.date=date;
        this.terrain = terrain;
    }

    public int getTerrain() {
        return terrain;
    }

    public void setTerrain(int terrain) {
        this.terrain = terrain;
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
