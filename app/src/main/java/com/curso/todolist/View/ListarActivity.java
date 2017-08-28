package com.curso.todolist.View;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.curso.todolist.Adapter.TaskAdapter;
import com.curso.todolist.Fragment.OptionFragment;
import com.curso.todolist.Model.Task;
import com.curso.todolist.Persistence.TaskRepository;
import com.curso.todolist.R;

import static android.R.id.list;

public class ListarActivity extends ListActivity {

    public final static String OBJECT_SERIALIZABLE = "taskObject";
    private final static String TAG_FRAGMENT = "dialogFragment";
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

        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Task task = (Task) taskAdapter.getItem(i);
                ShowDialog(task);

            }
        });
    }

    void ShowDialog(Task task) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Fragment fragment = getFragmentManager().findFragmentByTag(TAG_FRAGMENT);
        if (fragment != null)
            fragmentTransaction.remove(fragment);
        fragmentTransaction.addToBackStack(TAG_FRAGMENT);

        DialogFragment dialogFragment = new OptionFragment();
        sendObjectToDialog(OBJECT_SERIALIZABLE, dialogFragment, task);
        dialogFragment.show(fragmentTransaction, TAG_FRAGMENT);
    }

    protected void sendObjectToDialog(String msgObject, Fragment fragment, Task task) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(msgObject, task);
        fragment.setArguments(bundle);
    }


}