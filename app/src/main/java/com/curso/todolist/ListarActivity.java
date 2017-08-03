package com.curso.todolist;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;


public class ListarActivity extends AppCompatActivity {

    SQLiteDatabase bancoDados;
    private ListView listaTarefas;
    private ArrayAdapter<String> stringArrayAdapter;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        listaTarefas = (ListView) findViewById(R.id.listView_id);
        bancoDados = openOrCreateDatabase("appTarefa", MODE_PRIVATE, null);

        try {
            stringArrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1 , ListarBanco());
            listaTarefas.setAdapter(stringArrayAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public ArrayList<String> ListarBanco() {
        ArrayList<String> lista = new ArrayList<>();
        cursor = bancoDados.rawQuery("SELECT * FROM tabela ORDER BY id DESC", null);
        int indColunaId = cursor.getColumnIndex("id");
        int indColunaNome = cursor.getColumnIndex("nome");
        int indColunaTarefa = cursor.getColumnIndex("tarefa");
        cursor.moveToFirst();
        int cont = cursor.getCount();
        int i = 0;
            while ( i < cont) {
                int tmpId = cursor.getInt(indColunaId);
                String tmpNome = cursor.getString(indColunaNome);
                String tmpTarefa = cursor.getString(indColunaTarefa);
                String message = "Tarefa: " + tmpId + "\nTítulo: " + tmpNome + "\nResumo: " + tmpTarefa;                        ;
                lista.add(message);
                cursor.moveToNext();
                i++;
            }
        return lista;
    }
}

