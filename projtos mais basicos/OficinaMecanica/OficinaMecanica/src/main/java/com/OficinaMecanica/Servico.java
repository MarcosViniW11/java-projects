package com.OficinaMecanica;



public class Servico {
    private int id;
    private String nome;
    private String observacao;
    private double valor;

    public Servico(){}

    public Servico(String nome, String observacao, double valor) {
        this.nome = nome;
        this.observacao = observacao;
        this.valor = valor;
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
    public String getObservacao() {
        return observacao;
    }
    public void setDescricao(String observacao) {
        this.observacao = observacao;
    }
    public double getValor() {
        return valor;
    }
    public void setPreco(double valor) {
        this.valor = valor;
    }
    @Override
    public String toString() {
        return id +" nome do serviço: "+nome+" Observação: "+observacao+" Valor: "+valor;
    }



}
