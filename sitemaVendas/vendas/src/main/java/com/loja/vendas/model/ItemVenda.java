package com.loja.vendas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "itens_venda")
public class ItemVenda {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Produto produto;

    private int quantidadeVendida;

    private double subTotal;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "venda_id")
    private Venda venda;

}
