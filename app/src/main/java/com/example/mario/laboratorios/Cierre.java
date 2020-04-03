
/*
 * Nombre: Mario Olivera Castañeda
 * Fecha: 25/01/2019
 * Programa: Gestión de laboratorios
 */
package com.example.mario.laboratorios;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mario.laboratorios.Entidades.Laboratorios;

import java.util.ArrayList;

public class Cierre extends AppCompatActivity {
    private ListView listadoLaboral;
    private ArrayList<String> listaInformacion;
    private ArrayList<Laboratorios> listaLaboratorios;
    private ConexionSQLiteHelper conn;
    private EditText cajaIdLaboratorio;
    private Button cierre;
    private Button apertura;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cierre);
        conn = new ConexionSQLiteHelper(getApplicationContext(), "laboratorios.db", null, 1);
        listadoLaboral = findViewById(R.id.listadelaboratorios);
        cajaIdLaboratorio = findViewById(R.id.cajaIdLaboratorio);
        cierre = findViewById(R.id.cierreLaboratorio);
        cierre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarCierre();
            }
        });
        apertura = findViewById(R.id.aperturaLaboratorio);
        apertura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarApertura();
            }
        });
        consultarListasLaboratorios();
        ArrayAdapter adaptador2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listadoLaboral.setAdapter(adaptador2);
    }
    //Para Consultar los laboratorios
    public void consultarListasLaboratorios(){
        SQLiteDatabase db = conn.getReadableDatabase();
        Laboratorios laboratorio = null;
        listaLaboratorios = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM laboratorios", null);
        while(cursor.moveToNext()){
            laboratorio = new Laboratorios();
            laboratorio.setId(cursor.getInt(0));
            laboratorio.setLaboratorio(cursor.getString(1));
            laboratorio.setCerrado(cursor.getInt(2));
            listaLaboratorios.add(laboratorio);
        }
        db.close();
        obtenerListaLaboratorio();
    }
    //Obtiene la lista de los laboratorios
    public void obtenerListaLaboratorio(){
        listaInformacion = new ArrayList<>();
        for (int i = 0; i < listaLaboratorios.size(); i++){
            String informacion = listaLaboratorios.get(i).getId() + " - " + listaLaboratorios.get(i).getLaboratorio();
            if(listaLaboratorios.get(i).isCerrado() == 0){
                informacion += " - cerrado";
            }
            else{
                informacion += " - abierto";
            }
            listaInformacion.add(informacion);
        }
    }
    //Actualiza el cierre si esta abierto el laboratorio
    public void actualizarCierre() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {cajaIdLaboratorio.getText().toString()};
        ContentValues values = new ContentValues();
        values.put("cierre", 0);
        db.update("laboratorios", values, "id = ?", parametros);
    }
    //Actualiza la apertura si esta cerrado el laboratorio
    public void actualizarApertura(){
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {cajaIdLaboratorio.getText().toString()};
        ContentValues values = new ContentValues();
        values.put("cierre", 1);
        db.update("laboratorios", values, "id = ?", parametros);
    }
}
