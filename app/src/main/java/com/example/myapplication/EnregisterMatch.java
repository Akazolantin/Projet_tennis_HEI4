package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

//import com.google.android.gms.common.api.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class EnregisterMatch extends AppCompatActivity {

    private static final String TAG = "match" ;
    Button validerButton;
    Button annulerButton;
    Button date;
    Spinner adversaireSpinner;
    Calendar calendar;
    DatePickerDialog datePicker;
    private NumberPicker nP1;
    private NumberPicker nP2;
    private NumberPicker nP3;
    private NumberPicker nP4;
    private NumberPicker nP5;
    private NumberPicker nP6;
    private ArrayList<String> joueurs= new ArrayList<>();
    private ArrayList<Integer> Ids = new ArrayList<Integer>();
    private String resultat_match;
    private String set1;
    private String set2;
    private String set3;
    private String set4;
    private String set5;
    private String set6;
    private String adv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregister_match);
        validerButton = (Button) findViewById(R.id.match_button_valider);
        annulerButton = (Button) findViewById(R.id.match_button_annuler);
        date = (Button) findViewById(R.id.match_date);
        adversaireSpinner = (Spinner) findViewById(R.id.match_adversaire);
        nP1 = findViewById(R.id.match_score_set1_j1);
        nP2 = findViewById(R.id.match_score_set1_j2);
        nP3 = findViewById(R.id.match_score_set2_j1);
        nP4 = findViewById(R.id.match_score_set2_j2);
        nP5 = findViewById(R.id.match_score_set3_j1);
        nP6 = findViewById(R.id.match_score_set3_j2);


        nP1.setMaxValue(7);
        nP1.setMinValue(0);

        nP2.setMaxValue(7);
        nP2.setMinValue(0);

        nP3.setMaxValue(7);
        nP3.setMinValue(0);

        nP4.setMaxValue(7);
        nP4.setMinValue(0);

        nP5.setMaxValue(7);
        nP5.setMinValue(0);

        nP6.setMaxValue(7);
        nP6.setMinValue(0);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int lday = calendar.get(Calendar.DAY_OF_MONTH);
                int lmonth = calendar.get(Calendar.MONTH);
                int lyear = calendar.get(Calendar.YEAR);

                datePicker = new DatePickerDialog(EnregisterMatch.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                    }
                }, lday, lmonth, lyear);
                datePicker.show();
            }
        });
        nP1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                set1=String.valueOf(newVal);
            }
        });
        nP2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                set2=String.valueOf(newVal);
            }
        });
        nP3.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                set3=String.valueOf(newVal);
            }
        });
        nP4.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                set4=String.valueOf(newVal);
            }
        });
        nP5.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                set5=String.valueOf(newVal);
            }
        });
        nP6.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                set6=String.valueOf(newVal);
            }
        });


        validerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(EnregisterMatch.this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
                    RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                    resultat_match=set1+"/"+set2+"_"+set3+"/"+set4+"_"+set5+"/"+set6;
                    //date_mois=date.getText().toString();
                     adv = adversaireSpinner.getSelectedItem().toString();
                    String URL = "http://10.224.0.130/tennis.php?Rec_score=true&res="+resultat_match+"&Identifiant_1="+SIngleton.getId()+"&mois=4&jour=7&Identifiant_2=1";
                    Log.d(TAG, resultat_match);
                    Log.d(TAG, URL);
                    if(adversaireSpinner.getSelectedItem()!="Pas d'adversaire"){
                        URL+=Ids.get(adversaireSpinner.getSelectedItemPosition());
                    }else{URL+="NULL";}

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Intent Acitivity2 = new Intent(getApplicationContext(), PageAcceuil.class);
                            startActivity(Acitivity2);

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(EnregisterMatch.this, "Erreur lors du chargement.", Toast.LENGTH_SHORT).show();
                        }
                    });
                    queue.add(stringRequest);
                } else {
                    Toast.makeText(EnregisterMatch.this, "Vous n'avez pas donné la permission.", Toast.LENGTH_SHORT).show();
                }
            }
    });



        annulerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Annulation = new Intent(getApplicationContext(), PageAcceuil.class);
                startActivity(Annulation);
            }
        });
        joueurs.add("pas de joueur");
        if (ActivityCompat.checkSelfPermission(EnregisterMatch.this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
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
                    Toast.makeText(EnregisterMatch.this, "Erreur lors du chargement.", Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(stringRequest);
        } else{
            Toast.makeText(EnregisterMatch.this, "Vous n'avez pas donné la permission.", Toast.LENGTH_SHORT).show();
        }
        //System.out.println(joueurs);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, joueurs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adversaireSpinner.setAdapter(adapter);


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
