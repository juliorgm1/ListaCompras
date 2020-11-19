package com.example.listacompras.model;

import java.util.List;

public class ListaDeCompras {
    private String nome;
    private List<Item> itens;

    public ListaDeCompras() {
    }

    public ListaDeCompras(String nome, List<Item> itens) {
        this.nome = nome;
        this.itens = itens;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
}
