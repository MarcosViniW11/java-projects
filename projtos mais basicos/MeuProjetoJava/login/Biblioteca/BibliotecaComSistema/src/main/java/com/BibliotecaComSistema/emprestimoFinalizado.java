package com.BibliotecaComSistema;

import java.util.Date;

public class emprestimoFinalizado {
    private int id;
    private int idUsuario;
    private int idLivro;
    private Date dataEmprestimo;
    private Date dataDevolucao;

    public emprestimoFinalizado() {};

    public emprestimoFinalizado(int id,int idUsuario,int idLivro,Date dataEmprestimo,Date dataDevolucao) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idLivro = idLivro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
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
    @Override
    public String toString() {
        return id+" ID do usuario: "+idUsuario+" ID do livro: "+idLivro+" Data do emprestimo: "+dataEmprestimo+" Data de devolucao: "+dataDevolucao;
    }
}
