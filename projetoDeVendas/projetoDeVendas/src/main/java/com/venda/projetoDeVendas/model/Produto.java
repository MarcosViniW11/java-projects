package com.venda.projetoDeVendas.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private double precoUnitario;

    @Column(nullable = false)
    private int quantidadeEstoque;

}
