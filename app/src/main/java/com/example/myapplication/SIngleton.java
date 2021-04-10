package com.example.myapplication;

import java.util.ArrayList;

public class SIngleton {
    private static ArrayList<Hour> heures = new ArrayList<Hour>();
    private static int nbrTerrains=6;
    private static int id=0;
    private static int mois;

    private static boolean admin;

    public static boolean isAdmin() {
        return admin;
    }

    public static void setAdmin(boolean admi) {
        admin = admi;
    }

    public static int getMois() {
        return mois;
    }

    public static void setMois(int moi) {
        mois = moi;
    }

    private static String Tag="SIngleton";

    public static void setHeures(ArrayList<Hour> heures) {
        SIngleton.heures = heures;
    }

    public static int getId() {
        return id;
    }
    public static void setId(int ID) {
        id = ID;
    }

    public static void addHour(Hour heure){
        heures.add(heure);
    }
    public static Hour getHourById(int i){
        return heures.get(i);
    }

    public static boolean isHourFull(int date,int hour){
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
    public static boolean isDayFull(int date){
        boolean dayFull=true;
        for(int i=8;i<=20;i++){
            if(!isHourFull(date,i)){
                dayFull=false;
            }
        }
        return dayFull;
    }

    public static boolean isHourReserved(int date,int hour){
        for (Hour heure:heures){
            if (heure.isParticipation()&&date==heure.getDate()&&hour==heure.getHour()) {
                return true;
            }
        }
            return false;
    }
    public static boolean isDayReserved(int date){
        for(int i=8;i<=20;i++){
            if(isHourReserved(date,i)){
                return true;
            }
        }
        return false;
    }
    public static ArrayList<Integer> getTerrain(int date, int hour) {
        ArrayList<Integer> terrains = new ArrayList<Integer>();
        for (int i = 0; i < nbrTerrains; i++) {
            terrains.add(i);
        }
        for (Hour heure : heures) {
            for (int i = 1; i < nbrTerrains; i++) {
                if (date == heure.getDate() && hour == heure.getHour() && terrains.get(i) == heure.getTerrain()) {
                    terrains.remove(i);
                }
            }

        }
        return terrains;
    }

    public static int getNbrTerrains() {
        return nbrTerrains;
    }

    public static void setNbrTerrains(int nbrTerrains) {
        if(nbrTerrains>0 &&nbrTerrains<=25) {
            SIngleton.nbrTerrains = nbrTerrains;
        }
    }
}
