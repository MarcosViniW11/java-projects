package com.BibliotecaComSistema;

import java.util.Date;

public class EmprestimoDetalhe {
    private int id;
    private String usuarioNome;
    private String livroTitulo;
    private Date dataEmprestimo;
    private Date dataPrevista;
    private Date dataDevolucao;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUsuarioNome() {
        return usuarioNome;
    }
    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }
    public String getLivroTitulo() {
        return livroTitulo;
    }
    public void setLivroTitulo(String livroTitulo) {
        this.livroTitulo = livroTitulo;
    }
    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }
    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    public Date getDataPrevista() {
        return dataPrevista;
    }
    public void setDataPrevista(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }
    public Date getDataDevolucao() {
        return dataDevolucao;
    }
    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }


    // Getters, setters e toString()
    @Override
    public String toString() {
        return "ID Empréstimo: " + id +
                "\nUsuário: " + usuarioNome +
                "\nLivro: " + livroTitulo +
                "\nData Empréstimo: " + dataEmprestimo +
                "\nData Prevista: " + dataPrevista +
                "\nData Devolução: " + dataDevolucao +
                "\n-----------------------------------";
    }
}
