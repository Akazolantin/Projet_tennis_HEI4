package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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


        if (ActivityCompat.checkSelfPermission(Reservation.this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String URL = "http://10.224.0.130/tennis.php?Liste_Joueurs=true";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonarray = new JSONArray(response);

                        for (int i=0;i<=jsonarray.length();i++) {
                            JSONObject data = jsonarray.getJSONObject(i);
                            joueurs.add(data.getString("Identifiant"));

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
        //System.out.println(joueurs);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, joueurs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adversaireSpinner.setAdapter(adapter);

        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, SIngleton.getTerrain(intdate,heure));
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        terrainSpinner.setAdapter(adapter);
    }
}
