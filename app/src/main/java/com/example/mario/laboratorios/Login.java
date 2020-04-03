package com.example.mario.laboratorios;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mario.laboratorios.Entidades.Personal;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private ArrayList<EditText> cajas = new ArrayList<>();
    private RelativeLayout layoutLogin;
    private Button acceder;
    private ConexionSQLiteHelper conn;
    private ArrayList<Boolean> accesos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        conn = new ConexionSQLiteHelper(getApplicationContext(), "laboratorios.db", null, 1);
        acceder = findViewById(R.id.btnLogin);
        layoutLogin = findViewById(R.id.layoutLogin);
        rellenarCajasLogin();
        acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                consultarLogin();
                consultarApertura();
                if(accesos.get(0) == true && accesos.get(1) == true){
                    Toast.makeText(Login.this, "Puede acceder al laboratorio", Toast.LENGTH_SHORT).show();
                }
                else if(accesos.get(0) == true && accesos.get(1) == false){
                    Toast.makeText(Login.this, "No puede acceder porque el laboratorio está cerrado", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Login.this, "Acceso denegado", Toast.LENGTH_SHORT).show();
                }
                accesos.clear();
            }
        });
    }
    public void rellenarCajasLogin(){
        for( int i = 0; i < layoutLogin.getChildCount(); i++ ) {
            if (layoutLogin.getChildAt(i) instanceof EditText) {
                cajas.add((EditText) layoutLogin.getChildAt(i));
            }
        }
    }
    public void consultarLogin(){
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM personal, laboratorios WHERE personal.id = laboratorios.id AND nombre LIKE '"+cajas.get(0).getText().toString()+"' " +
                "AND laboratorio LIKE '"+ cajas.get(1).getText().toString() +"'", null);
        if(cursor.moveToNext()){
            accesos.add(true);
        }
        else{
            accesos.add(false);
        }
        db.close();
    }
    public void consultarApertura(){
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM laboratorios WHERE cierre = 1" +
                " AND laboratorio LIKE '"+ cajas.get(1).getText().toString() +"'", null);
        if(cursor.moveToNext()){
            accesos.add(true);
        }
        else{
            accesos.add(false);
        }
        db.close();
    }
}
