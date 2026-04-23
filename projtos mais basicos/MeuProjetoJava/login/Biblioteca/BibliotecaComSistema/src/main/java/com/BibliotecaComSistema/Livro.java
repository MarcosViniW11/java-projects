package com.BibliotecaComSistema;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private int quantidade;

    public Livro(String titulo, String autor, int anoPublicacao, int quantidade) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.quantidade = quantidade;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public int getAnoPublicacao() {
        return anoPublicacao;
    }
    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    @Override
    public String toString() {
        return id+" titulo: "+titulo+" autor: "+autor+" anoPublicacao: "+anoPublicacao+" quantidade: "+quantidade;
    }



}
