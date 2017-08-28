package com.curso.todolist.Fragment;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.curso.todolist.Model.Task;
import com.curso.todolist.R;

import static com.curso.todolist.View.ListarActivity.OBJECT_SERIALIZABLE;

/**
 * Created by root on 27/08/17.
 */

public class OptionFragment extends DialogFragment {
    private static final String LOG_EVENT = "OptionFragment Log: ";

    public OptionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(LOG_EVENT, "onCreate fragmentDialog");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Task task = (Task) getArguments().getSerializable(OBJECT_SERIALIZABLE);
        View view = inflater.inflate(R.layout.option_fragment, container, false);

        TextView title = view.findViewById(R.id.title_task);
        Button buttonChangeStatus = view.findViewById(R.id.change_status_button);
        Button buttonUpdateTask = view.findViewById(R.id.update_task_button);
        Button buttonCancel = view.findViewById(R.id.cancel_button);

        title.setText(String.format("Tarefa: %s", task.getTitle()));

        return view;
    }
}
