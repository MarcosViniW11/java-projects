package com.bibliotecadb2;

import java.util.Date;

public class Emprestimo {
    private int id;
    private int livroId;
    private int usuarioId;
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private boolean devolvido;

    // Construtores
    public Emprestimo() {}

    public Emprestimo(int id, int livroId, int usuarioId, Date dataEmprestimo, Date dataDevolucao, boolean devolvido) {
        this.id = id;
        this.livroId = livroId;
        this.usuarioId = usuarioId;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.devolvido = devolvido;
    }

    public Emprestimo(int livroId, int usuarioId, Date dataEmprestimo, Date dataDevolucao, boolean devolvido) {
        this.livroId = livroId;
        this.usuarioId = usuarioId;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.devolvido = devolvido;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getLivroId() { return livroId; }
    public void setLivroId(int livroId) { this.livroId = livroId; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public Date getDataEmprestimo() { return dataEmprestimo; }
    public void setDataEmprestimo(Date dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }

    public Date getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(Date dataDevolucao) { this.dataDevolucao = dataDevolucao; }

    public boolean isDevolvido() { return devolvido; }
    public void setDevolvido(boolean devolvido) { this.devolvido = devolvido; }

    // toString para debug
    @Override
    public String toString() {
        return "Emprestimo{" +
                "id=" + id +
                ", livroId=" + livroId +
                ", usuarioId=" + usuarioId +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                ", devolvido=" + devolvido +
                '}';
    }
}
