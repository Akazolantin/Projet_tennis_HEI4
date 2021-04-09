package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class Pageconnexion extends AppCompatActivity {
    private Button bouton;
    private static String Tag = "Test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pageconnexion);
        EditText identView = findViewById(R.id.username);
        EditText identmotdepasse = findViewById(R.id.password);
        this.bouton = (Button) findViewById(R.id.button);
        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(Pageconnexion.this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    String URL = "http://10.224.0.130/tennis.php?Identification=true&identifiant="+identView.getText().toString()+"&MotDePasse="+identmotdepasse.getText().toString();;
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                if(jsonToBoolean(response)){
                                    Intent Acitivity2 = new Intent(getApplicationContext(), PageAcceuil.class);
                                    startActivity(Acitivity2);
                                } else {
                                    Toast.makeText(Pageconnexion.this, "Votre mot de passe ou identifiant est incorrect", Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Pageconnexion.this, "Erreur lors du chargement.", Toast.LENGTH_SHORT).show();
                        }
                    });
                    queue.add(stringRequest);
                } else{
                    Toast.makeText(Pageconnexion.this, "Vous n'avez pas donné la permission.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Boolean jsonToBoolean (String data) throws JSONException {
        Log.d(Tag,data);
        Boolean connection;
            JSONObject object = new JSONObject(data);
            connection = object.getBoolean("Verification");
        Log.d(Tag, String.valueOf(connection));
        return connection;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pageconnexion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_informations:
                Intent Acitivity2 = new Intent(getApplicationContext(), Information.class);
                startActivity(Acitivity2);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        boolean permissionGranted = false;
        switch(requestCode){
            case 9:
                permissionGranted = grantResults[0]== PackageManager.PERMISSION_GRANTED;
                break;
            default:
                permissionGranted = false;
        }
        if(permissionGranted){
        }else {
            Toast.makeText(this, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }
}