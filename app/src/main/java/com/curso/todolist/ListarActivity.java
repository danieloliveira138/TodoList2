package com.curso.todolist;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;


public class ListarActivity extends AppCompatActivity {

    SQLiteDatabase bancoDados;
    private ListView listaTarefas;
    private TarefaAdapter tarefaAdapter;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        listaTarefas = (ListView) findViewById(R.id.listView_id);
        bancoDados = openOrCreateDatabase("appTarefa", MODE_PRIVATE, null);

        try {
            tarefaAdapter = new TarefaAdapter (getApplicationContext(), ListarBanco());
            listaTarefas.setAdapter(tarefaAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public List<Tarefa> ListarBanco() {
        List<Tarefa> lista = new ArrayList<>();
        cursor = bancoDados.rawQuery("SELECT * FROM tabela ORDER BY id DESC", null);
        int indColunaId = cursor.getColumnIndex("id");
        int indColunaNome = cursor.getColumnIndex("nome");
        int indColunaTarefa = cursor.getColumnIndex("tarefa");
        cursor.moveToFirst();
        int cont = cursor.getCount();
        int i = 0;
            while ( i < cont) {
                Tarefa tmpTarefa = new Tarefa();
                tmpTarefa.setIndice(cursor.getInt(indColunaId));
                tmpTarefa.setTitulo(cursor.getString(indColunaNome));
                tmpTarefa.setResumo(cursor.getString(indColunaTarefa));
                lista.add(tmpTarefa);
                cursor.moveToNext();
                i++;
            }
        return lista;
    }
}

