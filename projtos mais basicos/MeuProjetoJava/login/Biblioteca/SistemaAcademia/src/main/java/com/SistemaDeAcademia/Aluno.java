package com.SistemaDeAcademia;

public class Aluno {
    private int id;
    private String nome;
    private int idade;
    private String plano;
    private String status;

    public Aluno(String nome, int idade, String plano, String status) {
        this.nome = nome;
        this.idade = idade;
        this.plano = plano;
        this.status = status;
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
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public String getPlano() {
        return plano;
    }
    public void setPlano(String plano) {
        this.plano = plano;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return id+" Nome: "+nome+" Idade: "+idade+" Plano: "+plano+" Status: "+status;
    }

}
