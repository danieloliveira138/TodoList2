package com.curso.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by root on 03/08/17.
 */

public class TarefaAdapter extends BaseAdapter {

    private Context context;
    private List<Tarefa> listaTarefa;

    public TarefaAdapter(Context context, List<Tarefa> listaTarefa){
        this.context = context;
        this.listaTarefa = listaTarefa;
    }

    @Override
    public int getCount() {
        return listaTarefa.size();
    }

    @Override
    public Object getItem(int i) {
        return listaTarefa.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        Tarefa tarefa = listaTarefa.get(i);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_view_custumizado_model_1, null);

        TextView numero = (TextView)view.findViewById(R.id.numero_id);
        numero.setText(String.valueOf(tarefa.getIndice()));


        TextView titulo = (TextView)view.findViewById(R.id.titulo_tarefa_id);
        titulo.setText(tarefa.getTitulo());

        TextView resumo = (TextView)view.findViewById(R.id.resumo_id);
        resumo.setText(tarefa.getResumo());

        return view;
    }
}

