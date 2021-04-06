package com.example.myapplication;

import java.util.ArrayList;

public class SIngleton {
    private ArrayList<Hour> heures = new ArrayList<Hour>();
    private int nbrTerrains=6;
    public void addHour(Hour heure){
        heures.add(heure);
    }
    public Hour getHourById(int i){
        return heures.get(i);
    }

    public boolean isHourFull(int date,int hour){
        int compteur=0;
        for (Hour heure:heures){
            if (heure.getHour()==hour&&heure.getDate()==date){compteur++;}
        }
        if(compteur>=nbrTerrains){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isDayFull(int date){
        boolean dayFull=true;
        for(int i=8;i<=20;i++){
            if(!isHourFull(date,i)){
                dayFull=false;
            }
        }
        return dayFull;
    }
}
