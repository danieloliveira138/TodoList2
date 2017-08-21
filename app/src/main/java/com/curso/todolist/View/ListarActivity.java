package com.curso.todolist.View;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.curso.todolist.Adapter.TaskAdapter;
import com.curso.todolist.Persistence.TaskRepository;
import com.curso.todolist.R;
import com.curso.todolist.Model.Task;
import static android.R.id.list;

public class ListarActivity extends ListActivity {

    TaskRepository mTaskRepository;
    private ListView taskList;
    private TaskAdapter taskAdapter;

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
    }
}