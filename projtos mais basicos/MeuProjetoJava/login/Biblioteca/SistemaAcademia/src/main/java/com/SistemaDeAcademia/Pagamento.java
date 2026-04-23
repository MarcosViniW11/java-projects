package com.SistemaDeAcademia;

import java.util.Date;

public class Pagamento {
    private int id;
    private int idAluno;
    private double valor;
    private Date dataPagamento;
    private Date dataVencimento;
    private String status;

    public Pagamento(int idAluno, double valor, Date dataPagamento, Date dataVencimento, String status) {
        this.idAluno = idAluno;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.dataVencimento = dataVencimento;
        this.status = status;
    }

    public Pagamento() {}

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdAluno() { return idAluno; }
    public void setIdAluno(int idAluno) { this.idAluno = idAluno; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public Date getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(Date dataPagamento) { this.dataPagamento = dataPagamento; }

    public Date getDataVencimento() { return dataVencimento; }
    public void setDataVencimento(Date dataVencimento) { this.dataVencimento = dataVencimento; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", idAluno=" + idAluno +
                ", valor=" + valor +
                ", dataPagamento=" + dataPagamento +
                ", dataVencimento=" + dataVencimento +
                ", status='" + status + '\'' +
                '}';
    }
}