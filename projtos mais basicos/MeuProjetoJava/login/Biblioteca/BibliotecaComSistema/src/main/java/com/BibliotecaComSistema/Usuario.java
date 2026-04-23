package com.BibliotecaComSistema;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private int telefone;

    public Usuario(String nome, String email, int telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getTelefone() {
        return telefone;
    }
    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
    @Override
    public String toString() {
        return id+" Nome: "+nome+" Email: "+email+" Telefone: "+telefone;
    }
}
