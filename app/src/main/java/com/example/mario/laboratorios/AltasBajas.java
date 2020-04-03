package com.example.mario.laboratorios;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
/*
 * Nombre: Mario Olivera Castañeda
 * Fecha: 25/01/2019
 * Programa: Gestión de laboratorios
 */
import java.util.ArrayList;

public class AltasBajas extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout altaBajas;
    private ArrayList<Button> botonesAltaBajas = new ArrayList<>();
    private String[] nombreBotones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.altas_bajas_personal);
        nombreBotones = getResources().getStringArray(R.array.botonesAltasBajas);
        altaBajas = findViewById(R.id.altasBajas);
        rellenarBotonesAltasBajas();
        nombrarBotones();
    }
    //Rellenar el array de botones
    public void rellenarBotonesAltasBajas(){
        for( int i = 0; i < altaBajas.getChildCount(); i++ ) {
            if (altaBajas.getChildAt(i) instanceof Button) {
                botonesAltaBajas.add((Button) altaBajas.getChildAt(i));
            }
        }
    }
    //Para ponerle los nombres a los botones
    public void nombrarBotones(){
        for(int i = 0; i < botonesAltaBajas.size(); i++){
            botonesAltaBajas.get(i).setText(nombreBotones[i]);
            botonesAltaBajas.get(i).setOnClickListener(this);
        }
    }
    //Evento on click de los botones
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.alta:
                Intent intent = new Intent(v.getContext(), Altas.class);
                startActivity(intent);
                break;
            case R.id.baja:
                Intent intent1 = new Intent(v.getContext(), Bajas.class);
                startActivity(intent1);
                break;
        }
    }
}
