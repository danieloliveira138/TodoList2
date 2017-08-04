package com.curso.todolist;

/**
 * Created by root on 02/08/17.
 */

public class Tarefa {
    private int indice;
    private String titulo;
    private String resumo;

    public Tarefa(int indice, String titulo, String resumo) {
        this.indice = indice;
        this.titulo = titulo;
        this.resumo = resumo;
    }

    public Tarefa(){

    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }
}
