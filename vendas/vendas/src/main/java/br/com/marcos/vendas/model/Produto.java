package br.com.marcos.vendas.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)//não pode estar em branco
    private String nome;

    @Column(nullable = false)
    private double precoUnitario;

    @Column(nullable = false)
    private int quantidadeEstoque;


}
