package com.loja02;

import java.util.Date;

public class Pedido {
    private int id;
    private int idCliente;
    private int idProduto;
    private int quantidade;
    private Date dataPedido;

    public Pedido(){

    }
    public Pedido(int idCliente, int idProduto, int quantidade, Date dataPedido){
        this.idCliente = idCliente;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.dataPedido = dataPedido;
    }
    public Pedido(int id,int idCliente, int idProduto, int quantidade, Date dataPedido){
        this.id = id;
        this.idCliente = idCliente;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.dataPedido = dataPedido;
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
    public int getIdProduto() {
        return idProduto;
    }
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
    public int getQuantidade() {
        return quantidade;

    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public Date getDataPedido() {
        return dataPedido;
    }
    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }
    @Override
    public String toString() {
        return "Pedido:\nid: " + id +
                "idCliente: " + idCliente +
                "idProduto: " + idProduto +
                "quantidade: " + quantidade+
                "Data:"+dataPedido;
    }
}
