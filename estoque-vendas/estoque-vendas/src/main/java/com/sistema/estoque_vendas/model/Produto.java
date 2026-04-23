package com.sistema.estoque_vendas.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private int quantidadeEstoque;

    @Column(nullable = false)
    private double precoUnitario;

    @Column(nullable = true)
    private boolean ativo = true;
}
