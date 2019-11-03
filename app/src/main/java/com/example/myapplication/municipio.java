package com.example.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class municipio extends AppCompatActivity {
    Button b1,b2,b3;
    EditText t1,t2;
    Spinner sp;
    ListView lista;
    private AdminSQLiteOpenHelper admin;
    private SQLiteDatabase bd;
    private Cursor fila;
    private ContentValues registro;
    String[] arreglo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipio);
        admin = new AdminSQLiteOpenHelper(this, vars.bd, null, vars.version);
        bd = admin.getWritableDatabase();
        t2= (EditText) findViewById(R.id.editText2);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        lista= (ListView) findViewById(R.id.lista);
        sp =(Spinner) findViewById(R.id.spinner);
        fila = bd.rawQuery("SELECT iddep AS _id, nombre FROM departamento ORDER BY nombre", null);
        SimpleCursorAdapter adapter2 = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_dropdown_item, fila,
                new String[] {"nombre"}, new int[] {android.R.id.text1}, SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bd = admin.getWritableDatabase();
                registro = new ContentValues();



                if (t2.getText().toString().length() == 0){
                    Toast.makeText(municipio.this, "Por favor llene el campo", Toast.LENGTH_SHORT).show();
                }else if ( t2!= null){
                    registro.put("nombre", t2.getText().toString());
                    registro.put("iddep",sp.getSelectedItemId());//nombre del campo

//nombre de la tabla
                    bd.insert("municipio", null, registro);

                    bd.close();
                    Toast.makeText(municipio.this, "REGISTRO ALMACENADO", Toast.LENGTH_LONG).show();

                    t2.setText("");
                }else {
                    Toast.makeText(municipio.this, "REGISTRO   NO ALMACENADO", Toast.LENGTH_LONG).show();
                }



            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bd!= null)
                {
                    admin = new AdminSQLiteOpenHelper(municipio.this, vars.bd, null, vars.version);
                    bd = admin.getWritableDatabase();

                    Cursor fila = bd.rawQuery("SELECT municipio.idmun,municipio.nombre,departamento.nombre FROM municipio,departamento where municipio.iddep= departamento.iddep", null);
                    int cantidad = fila.getCount();// cantidad de registro
                    int i=0;
                    arreglo = new String[cantidad];
                    if (fila.moveToFirst()) {
                        do {
                            String linea = fila.getInt(0) + "  municipio: " + fila.getString(1)+ "  departamento: " + fila.getString(2);
                            arreglo[i] = linea;
                            i++;
                        } while (fila.moveToNext());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(municipio.this, android.R.layout.simple_list_item_1, arreglo);
                    lista.setAdapter(adapter);


                }
            }
        });
    }
}

