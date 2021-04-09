package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Clientadapter  extends RecyclerView.Adapter<Clientadapter.MyViewHolder> {
    private List<Client> clientslist;
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView nom,prenom,email,telephone;

        public MyViewHolder( View view) {
            super(view);
            nom=view.findViewById(R.id.name);
           prenom=view.findViewById(R.id.prenom);
           email=view.findViewById(R.id.Email);
        }
    }
   public  Clientadapter(List<Client> clientList){
        this.clientslist=clientList;
   }

    @Override
    public Clientadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_client,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull Clientadapter.MyViewHolder holder, int position) {
        Client client=clientslist.get(position);
        holder.nom.setText(client.nom);
        holder.prenom.setText(client.prenom);
        holder.email.setText(client.email);
        holder.telephone.setText(client.telephone);

    }

    @Override
    public int getItemCount() {
        return clientslist.size() ;
    }
}