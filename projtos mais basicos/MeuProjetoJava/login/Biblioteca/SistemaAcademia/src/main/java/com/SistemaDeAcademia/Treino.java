package com.SistemaDeAcademia;

import java.util.Date;

public class Treino {
    private int id;
    private int idAluno;
    private String Descricao;
    private Date dataInicio;
    private Date dataFim;

    public  Treino(int idAluno, String Descricao, Date dataInicio, Date dataFim) {
        this.idAluno = idAluno;
        this.Descricao = Descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdAluno() {
        return idAluno;
    }
    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }
    public String getDescricao() {
        return Descricao;
    }
    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }
    public Date getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    public Date getDataFim() {
        return dataFim;
    }
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    @Override
    public String toString() {
        return "Treino {" +
                "ID = " + id +
                ", ID do Aluno = " + idAluno +
                ", Descrição = '" + Descricao + '\'' +
                ", Início = " + dataInicio +
                ", Fim = " + dataFim +
                '}';
    }
    }


