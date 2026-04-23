package com.OficinaMecanica;

public class Item_servico {
    private int id;
    private int idOrdemServico;
    private int idServico;
    private int quantidade;
    private double valorUnitario;
    private double subtotal;


    public Item_servico() {}

    public Item_servico(int idOrdemServico, int idServico, int quantidade){
        this.idOrdemServico = idOrdemServico;
        this.idServico = idServico;
        this.quantidade = quantidade;
    }

    public Item_servico(int idOrdemServico, int idServico, int quantidade, double valorUnitario, double subtotal) {
        this.idOrdemServico = idOrdemServico;
        this.idServico = idServico;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.subtotal = subtotal;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdOrdemServico() {
        return idOrdemServico;
    }
    public void setIdOrdemServico(int idOrdemServico) {
        this.idOrdemServico = idOrdemServico;
    }
    public int getIdServico() {
        return idServico;
    }
    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public double getValorUnitario() {
        return valorUnitario;
    }
    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    public double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    @Override
    public String toString() {
        return id+" {"+" ID da ordem de serviço: "+ idOrdemServico+" id do Serviço: "+idServico+" Quantidade: "+ quantidade+" Valor Unitario: "+valorUnitario+" Subtotal: "+subtotal+"}";
    }

}
