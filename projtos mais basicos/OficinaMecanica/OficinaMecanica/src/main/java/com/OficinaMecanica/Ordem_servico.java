package com.OficinaMecanica;

import java.util.Date;

public class Ordem_servico {
    private int id;
    private int idCliente;
    private int idVeiculo;
    private double total;
    private Date dataAbertura;
    private Date dataFechamento;
    private String status;
    private String observacoes;

    public Ordem_servico(){}

    public Ordem_servico(int idCliente,int idVeiculo,double total,Date dataAbertura,Date dataFechamento,String status,String observacoes) {
        this.idCliente = idCliente;
        this.idVeiculo = idVeiculo;
        this.total = total;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.status = status;
        this.observacoes = observacoes;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    public int getIdVeiculo() {
        return idVeiculo;
    }
    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }
    public Date getDataAbertura() {
        return dataAbertura;
    }
    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }
    public Date getDataFechamento() {
        return dataFechamento;
    }
    public void setDataFechamento(Date dataFechamento) {
        this.dataFechamento = dataFechamento;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public String toString() {
        return id+" id do cliente: "+idCliente+" id do veiculo: "+idVeiculo+" Total: "+total+" Data de Abertura: "+ dataAbertura+ " Data de fechamento: "+ dataFechamento+" status: "+status+" observacoes: "+observacoes;
    }


}
