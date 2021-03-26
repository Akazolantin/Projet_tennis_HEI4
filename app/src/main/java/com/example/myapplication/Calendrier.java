package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
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
    private RecyclerView recyclerViewD,recyclerViewG;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendrier);
        textedate = (TextView) findViewById(R.id.textView);
        Boutondate = (Button) findViewById(R.id.buttondate);
        Intent incomingIntent = getIntent();
        String date = incomingIntent.getStringExtra("date");
        textedate.setText(date);

        recyclerViewD = findViewById(R.id.recyclerD);
        recyclerViewG = findViewById(R.id.recyclerG);
        recyclerViewD.setHasFixedSize(true);
        recyclerViewG.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this);
        recyclerViewD.setLayoutManager(layoutManager1);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this);
        recyclerViewG.setLayoutManager(layoutManager2);
        RecyclerView.Adapter mAdapter = new MyAdapter();
        recyclerViewG.setAdapter(mAdapter);
        recyclerViewD.setAdapter(mAdapter);



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
            Hour hour=new Hour(9,2,date);
            holder.setHour(hour);
        }

        @Override
        public int getItemCount() {
            return 6;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            public MyViewHolder( View itemView) {
                super(itemView);
                LinearLayout linearLayout= itemView.findViewById(R.id.layout);
                TextView hour = itemView.findViewById(R.id.hour);
                TextView message = itemView.findViewById(R.id.message);
            }
            public void setHour(Hour hour){

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_calendrier, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_deconnexion:
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