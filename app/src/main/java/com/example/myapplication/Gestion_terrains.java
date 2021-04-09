package com.example.myapplication;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class Gestion_terrains extends AppCompatActivity  {
    private TextView nombre_terrains;
    private Button bouton_terrain_plus;
    private Button bouton_terrain_moins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_terrains);
        nombre_terrains=findViewById(R.id.terraindispo);
        nombre_terrains.setText(""+SIngleton.getNbrTerrains());
        bouton_terrain_plus=findViewById(R.id.button_plus);
        bouton_terrain_moins=findViewById(R.id.button_moins);
       bouton_terrain_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SIngleton.setNbrTerrains(SIngleton.getNbrTerrains()+1);
                nombre_terrains.setText(""+SIngleton.getNbrTerrains());
            }
        });

        bouton_terrain_moins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SIngleton.setNbrTerrains(SIngleton.getNbrTerrains()-1);
                nombre_terrains.setText(""+SIngleton.getNbrTerrains());
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gestionterrain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_deconnexion:
                Intent Acitivity1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(Acitivity1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}