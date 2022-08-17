package com.example.cadastroalunossqlite;

import java.io.Serializable;

public class Estudante implements Serializable {
    private int id;
    private String nome;
    private String curso;

    public Estudante() {
        this.id = 0;
        this.nome = "";
        this.curso = "";

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }





}
