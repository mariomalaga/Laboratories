
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
import com.example.mario.laboratorios.Entidades.Personal;

import java.util.ArrayList;

public class Permisos extends AppCompatActivity {
    private ListView listado;
    private ListView listadoLaboral;
    private ArrayList<String> listaInformacion;
    private ArrayList<String> listaInformacion2;
    private ArrayList<Personal> listaPersonales;
    private ArrayList<Laboratorios> listaLaboratorios;
    private ConexionSQLiteHelper conn;
    private EditText cajaId;
    private EditText cajaIdLaboratorio;
    private Button eliminar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permisos);
        conn = new ConexionSQLiteHelper(getApplicationContext(), "laboratorios.db", null, 1);
        listado = findViewById(R.id.listaPersonales);
        listadoLaboral = findViewById(R.id.listaLaboratorios);
        cajaIdLaboratorio = findViewById(R.id.idLaboratorio);
        cajaId = findViewById(R.id.idPersona);
        eliminar = findViewById(R.id.botonPermiso);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizar();
            }
        });
        consultarListasPersonas();
        consultarListasLaboratorios();
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listado.setAdapter(adaptador);
        ArrayAdapter adaptador2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion2);
        listadoLaboral.setAdapter(adaptador2);
    }
    //Para consultar la lista de las personas
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
    //Para consultar la lista de los laboratorios
    public void consultarListasLaboratorios(){
        SQLiteDatabase db = conn.getReadableDatabase();
        Laboratorios laboratorio = null;
        listaLaboratorios = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM laboratorios", null);
        while(cursor.moveToNext()){
            laboratorio = new Laboratorios();
            laboratorio.setId(cursor.getInt(0));
            laboratorio.setLaboratorio(cursor.getString(1));
            listaLaboratorios.add(laboratorio);
        }
        db.close();
        obtenerListaLaboratorio();
    }
    //Para obtener la lista de los laboratorios
    public void obtenerListaLaboratorio(){
        listaInformacion2 = new ArrayList<>();
        for (int i = 0; i < listaLaboratorios.size(); i++){
            listaInformacion2.add(listaLaboratorios.get(i).getId() + " - " + listaLaboratorios.get(i).getLaboratorio());
        }
    }
    //Para obtener la lista de las personas
    public void obtenerLista(){
        listaInformacion = new ArrayList<>();
        for (int i = 0; i < listaPersonales.size(); i++){
            listaInformacion.add(listaPersonales.get(i).getId() + " - " +listaPersonales.get(i).getNombre() + " - " + listaPersonales.get(i).getId_laboratorio());
        }
    }
    //Para actualizar la tabla personal
    public void actualizar(){
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {cajaId.getText().toString()};
        ContentValues values = new ContentValues();
        values.put("idlaboratorio", cajaIdLaboratorio.getText().toString());
        db.update("personal", values, "id = ?", parametros);
    }
}
