package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Reservation extends AppCompatActivity {

    private ArrayList<String> joueurs= new ArrayList<String>();
    private ArrayList<Integer> Ids = new ArrayList<Integer>();
    private String Tag ="Reservation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        TextView date = findViewById(R.id.match_date);
        Intent incomingIntent = getIntent();
        int heure = incomingIntent.getIntExtra("heure",8);
        int intdate = incomingIntent.getIntExtra("intDate",8);
        date.setText(incomingIntent.getStringExtra("date")+" : "+heure+"h");


        Spinner adversaireSpinner = (Spinner) findViewById(R.id.adversaire);
        Spinner terrainSpinner = (Spinner) findViewById(R.id.terrain);

        joueurs.add("Pas d'adversaire");
        Ids.add(0);
        if (ActivityCompat.checkSelfPermission(Reservation.this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String URL = "http://10.224.0.130/tennis.php?Liste_Joueurs=true";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonarray = new JSONArray(response);
                        for (int i=0;i<jsonarray.length();i++) {
                            JSONObject data = jsonarray.getJSONObject(i);
                            joueurs.add(data.getString("Identifiant"));
                            Ids.add(data.getInt("Id"));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Reservation.this, "Erreur lors du chargement.", Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(stringRequest);
        } else{
            Toast.makeText(Reservation.this, "Vous n'avez pas donné la permission.", Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, joueurs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adversaireSpinner.setAdapter(adapter);

        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, SIngleton.getTerrain(intdate,heure));
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        terrainSpinner.setAdapter(adapter2);

        Button button_annuler = findViewById(R.id.button_annuler);
        Button button_valider = findViewById(R.id.button_valider);

        button_annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Reservation.this,Pagecalendrier.class);
                startActivity(intent);
            }
        });

        button_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(Reservation.this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    String URL = "http://10.224.0.130/tennis.php?Reservation=true&mois="+SIngleton.getMois()+"&jour="+intdate+"&heure="+heure+"&id_joueur1="+SIngleton.getId()+"&terrain="+terrainSpinner.getSelectedItem()+"&id_joueur2=";
                    if(adversaireSpinner.getSelectedItem()!="Pas d'adversaire"){
                        URL+=Ids.get(adversaireSpinner.getSelectedItemPosition());
                    }else{URL+="NULL";}
                    Log.d(Tag,URL);
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(Reservation.this, "Votre réservation a bien été enregistrée", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Reservation.this,Pagecalendrier.class);
                            startActivity(intent);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Reservation.this, "Erreur lors du chargement.", Toast.LENGTH_SHORT).show();
                        }
                    });
                    queue.add(stringRequest);
                } else{
                    Toast.makeText(Reservation.this, "Vous n'avez pas donné la permission.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(SIngleton.getId() == 0) {
            getMenuInflater().inflate(R.menu.not_connected, menu);
        }else{
            if(SIngleton.isAdmin()){
                getMenuInflater().inflate(R.menu.admin, menu);
            }else{
                getMenuInflater().inflate(R.menu.connected, menu);
            }
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_admin:
                Intent Acitivity9 = new Intent(getApplicationContext(), Pageadministrateur.class);
                startActivity(Acitivity9);
                return true;
            case R.id.menu_inscription:
                Intent Acitivity8 = new Intent(getApplicationContext(), Inscription.class);
                startActivity(Acitivity8);
                return true;
            case R.id.connexion:
                Intent Acitivity7 = new Intent(getApplicationContext(), Pageconnexion.class);
                startActivity(Acitivity7);
                return true;
            case R.id.menu_calendrier:
                Intent Acitivity6 = new Intent(getApplicationContext(), Pagecalendrier.class);
                startActivity(Acitivity6);
                return true;
            case R.id.menu_accueil:
                Intent Acitivity5= new Intent(getApplicationContext(), MainActivity.class);
                if(SIngleton.getId() != 0) {
                    Acitivity5 = new Intent(getApplicationContext(), PageAcceuil.class);}
                startActivity(Acitivity5);
                return true;
            case R.id.menu_match:
                Intent Acitivity4 = new Intent(getApplicationContext(), EnregisterMatch.class);
                startActivity(Acitivity4);
                return true;
            case R.id.menu_deconnexion:
                SIngleton.setAdmin(false);
                SIngleton.setId(0);
                Intent Acitivity1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Acitivity1);
                return true;
            case R.id.menu_informations:
                Intent Acitivity2 = new Intent(getApplicationContext(), Information.class);
                startActivity(Acitivity2);
                return true;
            case R.id.menu_statistiques:
                Intent Acitivity3 = new Intent(getApplicationContext(), Statistique.class);
                startActivity(Acitivity3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
