package com.BibliotecaComSistema;

import java.util.Date;

public class Emprestimo {
    private int id;
    private int usuarioId;
    private int livroId;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private Date dataPrevista;

    public Emprestimo() {}
    public Emprestimo(int id, int usuarioId, int livroId, Date dataEmprestimo, Date dataDevolucao, Date dataPrevista) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.livroId = livroId;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }
    public Emprestimo(int usuarioId, int livroId, Date dataEmprestimo, Date dataDevolucao, Date dataPrevista) {
        this.usuarioId = usuarioId;
        this.livroId = livroId;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
    public int getLivroId() {
        return livroId;
    }
    public void setLivroId(int livroId) {
        this.livroId = livroId;
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
    public Date getDataPrevista() {
        return dataPrevista;
    }
    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    @Override
    public String toString() {
        return "Emprestimo{" + "id= " + id+ " UsuarioID: "+usuarioId+" LivroID: "+livroId+" Data Emprestimo: "+dataEmprestimo+" Data Devolucao: "+dataDevolucao+"Data Prevista: "+dataPrevista+'}';
    }

}
