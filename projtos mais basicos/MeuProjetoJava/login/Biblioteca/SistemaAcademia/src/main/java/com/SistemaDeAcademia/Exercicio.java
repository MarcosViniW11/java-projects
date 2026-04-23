package com.SistemaDeAcademia;

public class Exercicio {
    private int id;
    private int idTreino;
    private String nome;
    private int series;
    private int repetcoes;
    private String observacao;

    public Exercicio(int idTreino, String nome, int series, int repetcoes, String observacao) {
        this.idTreino = idTreino;
        this.nome = nome;
        this.series = series;
        this.repetcoes = repetcoes;
        this.observacao = observacao;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdTreino() {
        return idTreino;
    }
    public void setIdTreino(int idTreino) {
        this.idTreino = idTreino;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getSeries() {
        return series;
    }
    public void setSeries(int series) {
        this.series = series;
    }
    public int getRepetcoes() {
        return repetcoes;
    }
    public void setRepetcoes(int repetcoes) {
        this.repetcoes = repetcoes;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "{treino: "+id+"; ID Treino: "+idTreino+"; Nome do treino "+nome+"; Series: "+series+"; Repetições: "+repetcoes+"; Observacao: "+observacao+"}";
    }

}
