package com.ProjetoBibliotecaFinal;

import java.util.Date;

public class Emprestimo {
    private int id;
    private int idUsuario;
    private int idLivro;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private String statos;

    public Emprestimo(int idUsuario,int idLivro, Date dataEmprestimo, Date dataDevolucao, String statos) {
        this.idUsuario = idUsuario;
        this.idLivro = idLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.statos = statos;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public int getIdLivro() {
        return idLivro;
    }
    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }
    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }
    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    public Date getDataDevolucao() {
        return dataDevolucao;
    }
    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    public String getStatos() {
        return statos;
    }
    public void setStatos(String statos) {
        this.statos = statos;
    }
    @Override
    public String toString() {
        return id+". { id de Usuario: "+idUsuario+", id de Livro: "+idLivro+" Data de Emprestimo: "+dataEmprestimo+" Data de Devolucao: "+dataDevolucao+" Statos: "+statos+"}";
    }

}
