package com.venda.projetoDeVendas.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Produto produto;

    private double valorTotal;

    private int quantidadeASerVendido;


}
