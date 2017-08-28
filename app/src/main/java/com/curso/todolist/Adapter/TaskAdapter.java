package com.curso.todolist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.curso.todolist.Model.Task;
import com.curso.todolist.R;

import java.util.List;


public class TaskAdapter extends BaseAdapter {

    private Context context;
    private List<Task> taskList;

    public TaskAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int i) {
        return taskList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        Task task = taskList.get(i);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_costumize_adapter, null);

        TextView numero = view.findViewById(R.id.numero_id);
        if (task.getDone() == 0) {
            numero.setBackgroundResource(R.drawable.task);
            numero.setText(String.valueOf(task.getIndex()));
        } else {
            numero.setBackgroundResource(R.drawable.taskcomplete);
        }


        TextView titulo = view.findViewById(R.id.titulo_tarefa_id);
        titulo.setText(String.format("%d . %s\ncriado em: %s", task.getId(), task.getTitle(), task.getDate()));

        TextView resumo = view.findViewById(R.id.resumo_id);
        resumo.setText(task.getResume());

        TextView date = view.findViewById(R.id.date_task_id);
        date.setText(task.getDate());

        return view;
    }
}