package com.curso.todolist;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnCriarNota;
    private Button btnPesquisarNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
            SQLiteDatabase bancoDados = openOrCreateDatabase("appTarefa",MODE_PRIVATE,null);
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS tabela(" +
                    "                           id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "                           nome VARCHAR," +
                    "                           tarefa VARCHAR)");

        }
        catch (Exception e){
            e.printStackTrace();
        }
        btnCriarNota = (Button)findViewById(R.id.criar_nota_id);
        btnPesquisarNota = (Button)findViewById(R.id.ver_lista_id);

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
}
