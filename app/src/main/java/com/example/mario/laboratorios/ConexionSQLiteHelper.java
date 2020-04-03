/*
 * Nombre: Mario Olivera Castañeda
 * Fecha: 25/01/2019
 * Programa: Gestión de laboratorios
 */
package com.example.mario.laboratorios;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {
    private String CREAR_TABLA_LABORATORIOS = "CREATE TABLE laboratorios(id INTEGER PRIMARY KEY" +
            ", laboratorio TEXT, cierre INTEGER)";
    private String CREAR_TABLA_PERSONAL = "CREATE TABLE personal(id INTEGER PRIMARY KEY" +
            ", nombre TEXT, apellido TEXT, horaentrada INTEGER, horasalida INTEGER, idlaboratorio INTEGER)";
    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
//Evento On Create de SQLite
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAR_TABLA_LABORATORIOS);
        db.execSQL(CREAR_TABLA_PERSONAL);
    }
//Evento on upgrade de SQLite
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS laboratorios");
        db.execSQL("DROP TABLE IF EXISTS personal");
        onCreate(db);
    }
}
