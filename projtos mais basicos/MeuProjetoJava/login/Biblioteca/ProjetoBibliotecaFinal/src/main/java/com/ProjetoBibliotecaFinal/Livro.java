package com.ProjetoBibliotecaFinal;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private String categoria;
    private String statos;

    public Livro(String titulo, String autor, String categoria, String statos) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.statos = statos;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    public String getAutor(){
        return autor;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }
    public String getCategoria(){
        return categoria;
    }
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
    public String getStatos(){
        return statos;
    }
    public void setStatos(String statos){
        this.statos = statos;
    }
    @Override
    public String toString(){
        return id+" { titulo: "+ titulo+ "; autor: "+autor+"; categoria: "+categoria+"; statos: "+statos+" }";
    }


}
