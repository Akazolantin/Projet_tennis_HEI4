package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Calendrier extends AppCompatActivity{
    private static final String TAG = "Calendrier";
    private TextView textedate;
    private Button Boutondate;
    private RecyclerView recyclerView;
    private String date;
    private int intDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendrier);
        textedate = (TextView) findViewById(R.id.textView);
        Boutondate = (Button) findViewById(R.id.buttondate);
        Intent incomingIntent = getIntent();
        date = incomingIntent.getStringExtra("date");
        intDate = incomingIntent.getIntExtra("intDate",0);
        textedate.setText(date);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter mAdapter = new MyAdapter();
        recyclerView.setAdapter(mAdapter);



        Boutondate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Calendrier.this, Pagecalendrier.class);
                startActivity(intent);
                finish();
            }
        });

    }
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.hour, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder( MyAdapter.MyViewHolder holder, int position) {
            holder.setHour(position+8);
            holder.checkState(position);
        }

        @Override
        public int getItemCount() {
            return 12;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView hourText;
            TextView message;
            public MyViewHolder( View itemView) {
                super(itemView);
                hourText = itemView.findViewById(R.id.hour);
                message = itemView.findViewById(R.id.message);
            }
            public void setHour(int hour){
                hourText.setText(hour+" - ");
            }
            public void setLineColor(int color){
                this.itemView.setBackgroundColor(color);
            }
            public void checkState(int position){
                if (SIngleton.isHourFull(intDate,position+8)) {
                    setLineColor(Color.RED);
                    message.setText("Pas de terrains disponibles.");
                } else {
                    if (SIngleton.isHourReserved(intDate,position+8)) {
                        setLineColor(Color.BLUE);
                        message.setText("Vous avez r??serv?? un terrain.");
                    } else {
                        setLineColor(Color.GREEN);
                        message.setText("Il y a des terrains disponibles.");
                        this.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getApplicationContext(),Reservation.class);
                                intent.putExtra("date",date);
                                intent.putExtra("intDate",intDate);
                                intent.putExtra("heure",position+8);
                                startActivity(intent);
                            }
                        });
                    }
                }
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