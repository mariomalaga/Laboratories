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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mario.laboratorios.Entidades.Personal;

import java.util.ArrayList;

public class Listado extends AppCompatActivity {
    private ListView listado;
    private ArrayList<String> listaInformacion;
    private ArrayList<Personal> listaPersonales;
    private ConexionSQLiteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_personal);
        conn = new ConexionSQLiteHelper(getApplicationContext(), "laboratorios.db", null, 1);
        listado = findViewById(R.id.listadoPersonal);
        consultarListasPersonas();
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listado.setAdapter(adaptador);
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String informacion = "Nombre: " + listaPersonales.get(position).getNombre() + "\n";
                informacion += "Apellido: " + listaPersonales.get(position).getApellido() + "\n";
                informacion += "Hora Entrada: " + listaPersonales.get(position).getHoraEntrada() + "\n";
                informacion += "Hora Salida: " + listaPersonales.get(position).getHoraSalida() + "\n";

                Toast.makeText(getApplicationContext(), informacion, Toast.LENGTH_SHORT).show();
            }
        });
    }
    //Metodo para consultar todas las personas
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
    //Obtiene la lista de las personas
    public void obtenerLista(){
        listaInformacion = new ArrayList<>();
        for (int i = 0; i < listaPersonales.size(); i++){
            listaInformacion.add(listaPersonales.get(i).getNombre() + " - " + listaPersonales.get(i).getApellido());
        }
    }
}
