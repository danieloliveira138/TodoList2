package com.curso.todolist;

/**
 * Created by root on 02/08/17.
 */

public class Tarefa {
    private String id;
    private String nome;
    private String tarefa;

    public Tarefa(String a, String b, String c){
        this.id = a;
        this.nome = b;
        this.tarefa = c;
    }
    public Tarefa(){

    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }
}
