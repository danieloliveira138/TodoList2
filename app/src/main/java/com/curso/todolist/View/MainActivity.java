package com.curso.todolist.View;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.curso.todolist.R;


public class MainActivity extends AppCompatActivity {

    static String DATA_BASE_NAME = "appTarefa";
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCriarNota = (Button) findViewById(R.id.criar_nota_id);
        Button btnPesquisarNota = (Button) findViewById(R.id.ver_lista_id);

        try{
            database = openOrCreateDatabase(DATA_BASE_NAME, MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS tabela(" +
                    "                           id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "                           nome VARCHAR," +
                    "                           tarefa VARCHAR)");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        btnCriarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent activityCriar = new Intent(getApplicationContext(), CriarActivity.class);
                startActivity(activityCriar);
            }
        });
        btnPesquisarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pesquisar = new Intent(getApplicationContext(),ListarActivity.class);
                startActivity(pesquisar);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}
