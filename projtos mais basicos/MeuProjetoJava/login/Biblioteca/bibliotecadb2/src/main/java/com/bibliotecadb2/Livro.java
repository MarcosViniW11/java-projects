package com.bibliotecadb2;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private int ano_publicacao;
    private String genero;
    private int quantidade;

    public Livro(String titulo, String autor, int ano_publicacao, String genero, int quantidade) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano_publicacao = ano_publicacao;
        this.genero = genero;
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
    public int getAno_publicacao() {
        return ano_publicacao;
    }
    public void setAno_publicacao(int ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;

    }

    @Override
    public String toString() {
        return "Livro:{ "+id+" titulo: "+titulo+" autor: "+autor+" ano de publicação: "+ano_publicacao+" genero: "+genero+" quantidade: "+quantidade+" }";
    }

}
