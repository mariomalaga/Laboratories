/*
 * Nombre: Mario Olivera Castañeda
 * Fecha: 25/01/2019
 * Programa: Gestión de laboratorios
 */
package com.example.mario.laboratorios;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mario.laboratorios.Entidades.Personal;

import java.util.ArrayList;

public class Bajas extends AppCompatActivity{
    private ListView listado;
    private ArrayList<String> listaInformacion;
    private ArrayList<Personal> listaPersonales;
    private ConexionSQLiteHelper conn;
    private EditText cajaId;
    private Button eliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bajas);
        conn = new ConexionSQLiteHelper(getApplicationContext(), "laboratorios.db", null, 1);
        listado = findViewById(R.id.personales);
        cajaId = findViewById(R.id.cajaId);
        eliminar = findViewById(R.id.eliminar);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar();
            }
        });
        consultarListasPersonas();
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listado.setAdapter(adaptador);
    }
    //Para consultar el listado del personal
    public void consultarListasPersonas(){
        SQLiteDatabase db = conn.getReadableDatabase();
        Personal personal = null;
        listaPersonales = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM personal", null);
        while(cursor.moveToNext()){
            personal = new Personal();
            personal.setId(cursor.getInt(0));
            personal.setNombre(cursor.getString(1));
            personal.setApellido(cursor.getString(2));
            personal.setHoraEntrada(cursor.getInt(3));
            personal.setHoraSalida(cursor.getInt(4));
            personal.setId_laboratorio(cursor.getInt(5));
            listaPersonales.add(personal);
        }
        db.close();
        obtenerLista();
    }
    //Obtiene el listado completo
    public void obtenerLista(){
        listaInformacion = new ArrayList<>();
        for (int i = 0; i < listaPersonales.size(); i++){
            listaInformacion.add(listaPersonales.get(i).getId() + " - " + listaPersonales.get(i).getNombre());
        }
    }
    //Para eliminar una persona
    public void eliminar(){
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {cajaId.getText().toString()};
        db.delete("personal", "id = ?", parametros);
        Toast.makeText(getApplicationContext(), "Se eliminó el personal", Toast.LENGTH_SHORT).show();
        db.close();
    }
}
