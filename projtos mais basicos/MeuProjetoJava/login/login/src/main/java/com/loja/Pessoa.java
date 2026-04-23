package com.loja;

public class Pessoa {

    private int id;
    private String email;
    private String nome;

    public Pessoa(String email, String nome) {
        this.email = email;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "id=" + id + ", email=" + email + ", nome=" + nome + '}';
    }



}
