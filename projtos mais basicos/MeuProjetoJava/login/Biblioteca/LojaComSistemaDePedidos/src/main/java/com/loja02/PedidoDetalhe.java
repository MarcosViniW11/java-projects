package com.loja02;

import java.util.Date;

public class PedidoDetalhe {
    private int id;                // ID do pedido
    private int clienteId;         // ID do cliente
    private String clienteNome;    // Nome do cliente
    private int produtoId;         // ID do produto
    private String produtoNome;    // Nome do produto
    private int precoProduto;      // Preço unitário do produto
    private int quantidade;        // Quantidade pedida
    private Date dataPedido;       // Data do pedido
    private int estoqueAtual;      // Estoque atual do produto

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }
    public String getClienteNome() { return clienteNome; }
    public void setClienteNome(String clienteNome) { this.clienteNome = clienteNome; }
    public int getProdutoId() { return produtoId; }
    public void setProdutoId(int produtoId) { this.produtoId = produtoId; }
    public String getProdutoNome() { return produtoNome; }
    public void setProdutoNome(String produtoNome) { this.produtoNome = produtoNome; }
    public int getPrecoProduto() { return precoProduto; }
    public void setPrecoProduto(int precoProduto) { this.precoProduto = precoProduto; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public Date getDataPedido() { return dataPedido; }
    public void setDataPedido(Date dataPedido) { this.dataPedido = dataPedido; }
    public int getEstoqueAtual() { return estoqueAtual; }
    public void setEstoqueAtual(int estoqueAtual) { this.estoqueAtual = estoqueAtual; }

    // Método para calcular o valor total do pedido
    public int getValorTotal() {
        return precoProduto * quantidade;
    }

    @Override
    public String toString() {
        return "PedidoDetalhe{" +
                "id=" + id +
                ", clienteNome='" + clienteNome + '\'' +
                ", produtoNome='" + produtoNome + '\'' +
                ", precoProduto=" + precoProduto +
                ", quantidade=" + quantidade +
                ", dataPedido=" + dataPedido +
                ", estoqueAtual=" + estoqueAtual +
                ", valorTotal=" + getValorTotal() +
                '}';
    }
}
