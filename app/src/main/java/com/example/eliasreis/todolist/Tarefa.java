package com.example.eliasreis.todolist;

/**
 * Created by eliasreis on 02/12/2017.
 */

public class Tarefa {
    public int id;
    public String tarefa;
    public int feito;

    public Tarefa(int id, String nomeTarefa, int feito){
        this.id = id;
        this.tarefa = nomeTarefa;
        this.feito = feito;
    }

}
