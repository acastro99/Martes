package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    Button b1,b2;
    EditText t1;

    private AdminSQLiteOpenHelper admin;
    private SQLiteDatabase bd;

    private ContentValues registro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        admin = new AdminSQLiteOpenHelper(this, vars.bd, null, vars.version);
        bd = admin.getWritableDatabase();
        t1= (EditText) findViewById(R.id.editText);

        b1 = (Button) findViewById(R.id.button);
        b2 =(Button) findViewById(R.id.button4);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(MainActivity.this, vars.bd, null, vars.version);
                bd = admin.getWritableDatabase();
                registro = new ContentValues();
if (t1.getText().toString().length() == 0){
    Toast.makeText(MainActivity.this, "Por favor llene el campo", Toast.LENGTH_SHORT).show();
}else if ( t1!= null) {

                    registro.put("nombre", t1.getText().toString()); // nombre del campo de la tabla
                    bd.insert("usuario", null, registro);// la tabla

                    bd.close();
                    Toast.makeText(MainActivity.this, "REGISTRO ALMACENADO", Toast.LENGTH_LONG).show();


                    t1.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "REGISTRO   NO ALMACENADO", Toast.LENGTH_LONG).show();// indica una alerta de que se registro

                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Empleado.class);
                startActivity(i);
            }
        });

    }



}

