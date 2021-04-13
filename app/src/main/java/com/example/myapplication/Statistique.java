package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
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

public class Statistique extends AppCompatActivity {

    private TextView textDateMatch;
    private TextView textAdversaire;
    private TextView textScore;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter_recycler;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<String> joueurs = new ArrayList<>();
    ArrayList<String> score = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistique);
        textAdversaire=(TextView) findViewById(R.id.stats_adversaire);
        textDateMatch=(TextView) findViewById(R.id.stats_date);
        textScore=(TextView) findViewById(R.id.stats_score);

        recyclerView = findViewById(R.id.statistiques_recycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(false);
        adapter_recycler= new RecyclerAdapter();
        recyclerView.setAdapter(adapter_recycler);

        joueurs.add("pas de joueurs");
        score.add("");
        if (ActivityCompat.checkSelfPermission(Statistique.this, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String URL = "http://10.224.0.130/tennis.php?ViewScore=true&Identifiant_1=1&Identifiant_2=2&mois=4&jour=6";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONArray jsonarray = new JSONArray(response);
                        for (int i=0;i<jsonarray.length();i++) {
                            JSONObject data = jsonarray.getJSONObject(i);
                            //joueurs.add(data.getString("Identifiant_2"));
                            score.add(data.getString("Résultat"));
                            //Ids.add(data.getInt("Id"));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Statistique.this, "Erreur lors du chargement.", Toast.LENGTH_SHORT).show();
                }
            });
            queue.add(stringRequest);
        } else{
            Toast.makeText(Statistique.this, "Vous n'avez pas donné la permission.", Toast.LENGTH_SHORT).show();
        }
    }

    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new RecyclerViewHolder(inflater.inflate(R.layout.stats, parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerAdapter.RecyclerViewHolder holder, int position) {
            holder.setRecycler();
        }

        @Override
        public int getItemCount() {
            return 0;
        }

        public class RecyclerViewHolder extends RecyclerView.ViewHolder {

            private TextView textView_Adversaire;
            private TextView textView_score;
            private TextView textView_date;

            public RecyclerViewHolder(@NonNull View itemView) {
                super(itemView);
                textView_Adversaire=itemView.findViewById(R.id.stats_adversaire);
                textView_score=itemView.findViewById(R.id.stats_score);
                textView_date=itemView.findViewById(R.id.stats_date);
            }
            public void setRecycler(){
                textView_score.setText(score.get(1));
                textView_date.setText("05/06");
                textView_Adversaire.setText("ThomasCappe");

            }


        }
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