package com.curso.todolist.View;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.curso.todolist.Controler.TaskRepository;
import com.curso.todolist.R;
import com.curso.todolist.Controler.Task;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;


public class ListarActivity extends ListActivity {

    TaskRepository mTaskRepository;
    private ListView taskList;
    private TaskAdapter taskAdapter;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        taskList = findViewById(list);

        mTaskRepository = new TaskRepository(getApplicationContext());

        try {
            taskAdapter = new TaskAdapter(getApplicationContext(), mTaskRepository.searchTask(null));
            taskList.setAdapter(taskAdapter);

        } catch (Exception e) {
            e.printStackTrace();
        }

        taskList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    Task tmptarefa = (Task) taskAdapter.getItem(i);
                    mTaskRepository.delete(tmptarefa);
                    taskAdapter = new TaskAdapter(getApplicationContext(), mTaskRepository.searchTask(null));
                    taskList.setAdapter(taskAdapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

//        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Task task = (Task) taskAdapter.getItem(i);
//                Intent intent = new Intent(getApplicationContext(), CriarActivity.class);
//                intent.putExtra("task",task);
//                startActivity(intent);
//            }
//        });

    }
//    public List<Task> ListarBanco() {
//        List<Task> lista = new ArrayList<>();
//        cursor = bancoDados.rawQuery("SELECT * FROM tabela ORDER BY id DESC", null);
//        int indColumnId = cursor.getColumnIndex("id");
//        int indColumnTitle = cursor.getColumnIndex("title");
//        int indColumnResume = cursor.getColumnIndex("resume");
//        cursor.moveToFirst();
//        int i = 0;
//            while ( i < cursor.getCount()) {
//                Task tmpTarefa = new Task(
//                        cursor.getInt(indColumnId),
//                        cursor.getCount() - i++,
//                        cursor.getString(indColumnTitle),
//                        cursor.getString(indColumnResume)
//                );
//                lista.add(tmpTarefa);
//                cursor.moveToNext();
//            }
//        return lista;
//    }
//    public void Deletar(Task tmpTask){
//        bancoDados.execSQL("DELETE FROM tabela WHERE id = "+ tmpTask.getId() );
//        Toast.makeText(ListarActivity.this, tmpTask.getTitle() + " Removido Com sucesso", Toast.LENGTH_SHORT).show();
//    }
}