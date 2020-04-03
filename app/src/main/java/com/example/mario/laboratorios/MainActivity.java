/*
 * Nombre: Mario Olivera Castañeda
 * Fecha: 25/01/2019
 * Programa: Gestión de laboratorios
 */
package com.example.mario.laboratorios;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<Button> botonesMenu = new ArrayList<>();
    private RelativeLayout menu;
    private String[] nombreBotones;
    final Handler mHandler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "laboratorios.db", null, 1);
        nombreBotones = getResources().getStringArray(R.array.botonesMenu);
        menu = findViewById(R.id.menu);
        rellenarBotonesMenu();
        nombrarBotones();
        insertarBD();
        miHilo();
    }
    //Para rellenar el array de botones
    public void rellenarBotonesMenu(){
        for( int i = 0; i < menu.getChildCount(); i++ ) {
            if (menu.getChildAt(i) instanceof Button) {
                botonesMenu.add((Button) menu.getChildAt(i));
            }
        }
    }
    //Para poner los nombres a los botones
    public void nombrarBotones(){
        for(int i = 0; i < botonesMenu.size(); i++){
            botonesMenu.get(i).setText(nombreBotones[i]);
            botonesMenu.get(i).setOnClickListener(this);
        }
    }
    //Para que la base de datos tenga algunos datos
    public void insertarBD(){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "laboratorios.db", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        String insert = "INSERT INTO laboratorios (laboratorio,cierre) VALUES ('Quimica', 1)";
        String insert1 = "INSERT INTO laboratorios (laboratorio,cierre) VALUES ('Fisica', 0)";
        String insert2 = "INSERT INTO laboratorios (laboratorio,cierre) VALUES ('Biologia', 0)";
        String insert3 = "INSERT INTO laboratorios (laboratorio,cierre) VALUES ('Farmacia', 1)";
        String insert4 = "INSERT INTO personal (nombre, apellido, horaentrada, horasalida, idlaboratorio) VALUES ('Mario'," +
                "'Olivera', 10, 13, 1 )";
        String insert5 = "INSERT INTO personal (nombre, apellido, horaentrada, horasalida, idlaboratorio) VALUES ('Tania'," +
                "'Lopez', 9, 10, 2 )";
        String insert6 = "INSERT INTO personal (nombre, apellido, horaentrada, horasalida, idlaboratorio) VALUES ('Ezequiel'," +
                "'Villalobos', 14, 16, 3 )";
        String insert7 = "INSERT INTO personal (nombre, apellido, horaentrada, horasalida, idlaboratorio) VALUES ('Carlos'," +
                "'Cobos', 20, 22, 4 )";
        db.execSQL(insert);
        db.execSQL(insert1);
        db.execSQL(insert2);
        db.execSQL(insert3);
        db.execSQL(insert4);
        db.execSQL(insert5);
        db.execSQL(insert6);
        db.execSQL(insert7);
        db.close();
    }
    //Evento on click para los 4 botones
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.boton0:
                Intent intent = new Intent (v.getContext(), Listado.class);
                startActivity(intent);
                break;
            case R.id.boton3:
                Intent intent1 = new Intent (v.getContext(), AltasBajas.class);
                startActivity(intent1);
                break;
            case R.id.boton:
                Intent intent2 = new Intent (v.getContext(), Permisos.class);
                startActivity(intent2);
                break;
            case R.id.boton2:
                Intent intent3 = new Intent (v.getContext(), Cierre.class);
                startActivity(intent3);
                break;
            case R.id.boton1:
                Intent intent4 = new Intent (v.getContext(), Login.class);
                startActivity(intent4);
                break;
        }
    }
    //Donde se incia el hilo
    protected void miHilo(){
        Thread t = new Thread(){
            public void run(){
                try{
                    Thread.sleep(2000);
                }
                catch (InterruptedException e){//5
                    e.printStackTrace();
                }
                mHandler.post(ejecutarAccion);
            }
        };
        t.start();
    }
    //Ejecuta la acccion del hilo
    final Runnable ejecutarAccion =new Runnable (){
        public void run(){
            Toast.makeText(MainActivity.this, "Bienvenido al programa de laboratorios", Toast.LENGTH_SHORT).show();
        }
    };
}
