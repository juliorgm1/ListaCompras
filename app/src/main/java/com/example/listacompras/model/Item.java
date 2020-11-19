package com.example.listacompras.model;

import java.io.Serializable;

public class Item implements Serializable {
    private String nome;

    public Item() {
    }

    public Item(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
