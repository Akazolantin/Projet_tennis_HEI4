package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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
import org.w3c.dom.Text;

import java.util.Calendar;

public class Inscription extends AppCompatActivity {
    private static final String TAG = "hola";
    private Button buttonvalidation;
    private EditText text_nom;
    private EditText text_prenom;
    private Button text_date;
    private EditText text_email;
    private EditText text_identifiant;
    private EditText text_mdp;
    private EditText text_verif_mdp;
    private boolean back_answer = false;
    Calendar calendar;
    DatePickerDialog datePicker;
    String nom;
    String prenom;
    String date;
    String email;
    String identifiant;
    String mdp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        buttonvalidation = (Button) findViewById(R.id.inscription_button_inscription);
        text_nom=(EditText)findViewById(R.id.inscription_nom);
        text_prenom= (EditText) findViewById(R.id.inscription_prenom);
        text_date = (Button) findViewById(R.id.inscription_date_naissance);
        text_email= (EditText) findViewById(R.id.inscription_email);
        text_identifiant= (EditText) findViewById(R.id.inscription_identifiant);
        text_mdp=(EditText) findViewById(R.id.inscription_mdp);
        text_verif_mdp= findViewById(R.id.inscription_verif_mdp);

        text_date.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             calendar = Calendar.getInstance();
                                             int lday = calendar.get(Calendar.DAY_OF_MONTH);
                                             int lmonth = calendar.get(Calendar.MONTH);
                                             int lyear = calendar.get(Calendar.YEAR);

                                             datePicker = new DatePickerDialog(Inscription.this, new DatePickerDialog.OnDateSetListener() {
                                                 @Override
                                                 public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                                     text_date.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                                                 }
                                             }, lday, lmonth, lyear);
                                             datePicker.show();
                                         }
                                     });



                buttonvalidation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d(TAG, text_mdp.getText().toString()+text_verif_mdp.getText().toString());

                        if (TextUtils.isEmpty(text_nom.getText().toString()) || TextUtils.isEmpty(text_prenom.getText().toString()) || TextUtils.isEmpty(text_date.getText().toString()) || TextUtils.isEmpty(text_email.getText().toString()) || TextUtils.isEmpty(text_identifiant.getText().toString()) || TextUtils.isEmpty(text_mdp.getText().toString()) || TextUtils.isEmpty(text_verif_mdp.getText().toString())) {
                            Toast.makeText(Inscription.this, "Un ou plusieurs champ non rempli", Toast.LENGTH_LONG).show();
                        } else if (text_mdp.getText().toString().equals(text_verif_mdp.getText().toString()) == false) {
                            Toast.makeText(Inscription.this, "veuillez entrer deux mot de passe identiques", Toast.LENGTH_LONG).show();

                        } else {
                            if (ActivityCompat.checkSelfPermission(Inscription.this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                nom = text_nom.getText().toString();
                                prenom = text_prenom.getText().toString();
                                identifiant = text_identifiant.getText().toString();
                                date = text_date.getText().toString();
                                mdp = text_mdp.getText().toString();
                                email = text_email.getText().toString();
                                String URL = "http://10.224.0.130/tennis.php?NewUtilisateur=true&prenom=" + prenom + "&nom=" + nom + "&identifiant=" + identifiant + "&DateNaissance=" + date + "&motdepasse=" + mdp + "&mail=" + email + "&tel=123";
                                Log.d(TAG, mdp);
                                StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Toast.makeText(Inscription.this, "Inscription effectuée", Toast.LENGTH_SHORT).show();
                                        Intent Acitivity2 = new Intent(getApplicationContext(), PageAcceuil.class);
                                        startActivity(Acitivity2);

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(Inscription.this, "Erreur lors du chargement.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                queue.add(stringRequest);
                            } else {
                                Toast.makeText(Inscription.this, "Vous n'avez pas donné la permission.", Toast.LENGTH_SHORT).show();
                            }

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










