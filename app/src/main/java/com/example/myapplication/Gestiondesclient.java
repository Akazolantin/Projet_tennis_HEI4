package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Gestiondesclient extends AppCompatActivity {
    private List<Client> clients = new ArrayList<>();
    private RecyclerView recyclerView;
    private Clientadapter clientadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestiondesclient);
        recyclerView = findViewById(R.id.Recyclerview);
        clientadapter = new Clientadapter(clients);
        RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutmanager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(clientadapter);
        populate();

    }

    private void populate() {
        Client client1 = new Client("antoine", "Benoit", "antoine.Benoit@student.junia.com", "0646362728224");
        clients.add(client1);
        Client client = new Client("Mouna", "Boutbagha", "Mouna.Boutbagha@student.junia.com", "0646362728224");
        clients.add(client);


    }
}
