package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context, String nombre, CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table usuario ( iddep integer primary key autoincrement,nombre text)");
        db.execSQL(" create table empleados( idmun integer primary key autoincrement,nombre text, salario real, iddep INTEGER NOT NULL CONSTRAINT fk_id_dep REFERENCES usuario(iddep) ON DELETE CASCADE ON UPDATE CASCADE)");



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("drop table if exists usuario" );
        db.execSQL(" create table usuario ( iddep integer primary key autoincrement,nombre text)");
        db.execSQL("drop table if exists empleados" );
        db.execSQL(" create table empleados( idmun integer primary key autoincrement,nombre text, salario real, iddep INTEGER NOT NULL CONSTRAINT fk_id_dep REFERENCES usuario(iddep) ON DELETE CASCADE ON UPDATE CASCADE)");


    }
}