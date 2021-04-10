package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.Timer;

public class Codeportail extends AppCompatActivity {

    private String code;
    private String Tag = "CodePortail";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codeportail);
        EditText codeText = findViewById(R.id.code);
        Button button = findViewById(R.id.button_code);
        CountDownTimer timer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                reinit();
            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(code.equals(codeText.getText().toString())){
                    if (ActivityCompat.checkSelfPermission(Codeportail.this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    String URL = "http://10.224.0.130/tennis.php?Acces=true&etatAcces=1&Id="+SIngleton.getId();
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            timer.start();
                            Intent intent = new Intent(Codeportail.this,PageAcceuil.class);
                            startActivity(intent);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Codeportail.this, "Erreur lors du chargement.", Toast.LENGTH_SHORT).show();
                        }
                    });
                    queue.add(stringRequest);
                } else{
                    Toast.makeText(Codeportail.this, "Vous n'avez pas donné la permission.", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(Codeportail.this, code, Toast.LENGTH_SHORT).show();
                }}});

        if (ActivityCompat.checkSelfPermission(Codeportail.this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String URL = "http://10.224.0.130/tennis.php?Requete=true&etatReq=1&Id="+SIngleton.getId();
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject object = new JSONObject(response);
                        code = object.getString("Code");
                        Log.d(Tag,code);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Codeportail.this, "Erreur lors du chargement.", Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(stringRequest);
        } else{
            Toast.makeText(Codeportail.this, "Vous n'avez pas donné la permission.", Toast.LENGTH_SHORT).show();
        }
    }

    public void reinit(){
        if (ActivityCompat.checkSelfPermission(Codeportail.this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String URL = "http://10.224.0.130/tennis.php?Requete=true&etatReq=0&Id="+SIngleton.getId();
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Codeportail.this, "Erreur lors du chargement.", Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(stringRequest);

        queue = Volley.newRequestQueue(getApplicationContext());
        String URL2 = "http://10.224.0.130/tennis.php?Acces=true&etatAcces=0&Id="+SIngleton.getId();
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, URL2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Codeportail.this, "Erreur lors du chargement.", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest2);
    } else{
        Toast.makeText(Codeportail.this, "Vous n'avez pas donné la permission.", Toast.LENGTH_SHORT).show();
    }}

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