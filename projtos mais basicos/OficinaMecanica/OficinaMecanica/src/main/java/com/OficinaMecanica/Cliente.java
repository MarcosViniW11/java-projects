package com.OficinaMecanica;

public class Cliente {
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private String status;

    public Cliente(){}
    public Cliente(String nome, String telefone, String email, String endereco, String status) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
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
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return id+": Nome: "+nome+" telefone: "+telefone+" email: "+email+" endereco: "+endereco+" status: "+status;
    }
}
