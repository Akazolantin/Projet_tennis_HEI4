package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;

public class Statistique extends AppCompatActivity {

    private TextView textDateMatch;
    private TextView textAdversaire;
    private TextView textScore;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter_recycler;
    private RecyclerView.LayoutManager layoutManager;


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


        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.statistique, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_deconnexion:
                Intent Acitivity1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Acitivity1);
                return true;
            case R.id.menu_portail:
                Intent Acitivity5 = new Intent(getApplicationContext(), Codeportail.class);
                startActivity(Acitivity5);
                return true;
            case R.id.menu_enregister:
                Intent Acitivity6 = new Intent(getApplicationContext(), Enregistrementcarte.class);
                startActivity(Acitivity6);
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }
    }
}