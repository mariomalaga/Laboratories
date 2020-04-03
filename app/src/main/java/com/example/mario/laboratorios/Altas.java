/*
 * Nombre: Mario Olivera Castañeda
 * Fecha: 25/01/2019
 * Programa: Gestión de laboratorios
 */
package com.example.mario.laboratorios;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class Altas extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<EditText>cajas = new ArrayList<>();
    private Button boton;
    private RelativeLayout altasLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.altas);
        boton = findViewById(R.id.botonCrear);
        altasLayout = findViewById(R.id.altasLayout);
        boton.setOnClickListener(this);
        rellenarCajasTexto();

    }
    //Rellena el array de edittext con todos los que hay en la vista
    public void rellenarCajasTexto(){
        for( int i = 0; i < altasLayout.getChildCount(); i++ ) {
            if (altasLayout.getChildAt(i) instanceof EditText) {
                cajas.add((EditText) altasLayout.getChildAt(i));
            }
        }
    }
    //Eventi On click del boton
    @Override
    public void onClick(View v) {
        registrarPersonal();
    }
    //Metodo para insertar empleados
    public void registrarPersonal(){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "laboratorios.db", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        String insert = "INSERT INTO personal (nombre, apellido, horaentrada, horasalida, idlaboratorio) VALUES ('" + cajas.get(0).getText().toString() + "', " +
                "'" + cajas.get(1).getText().toString() + "', 10, 11," + cajas.get(2).getText().toString() + ")";
        db.execSQL(insert);
        db.close();
    }

}
