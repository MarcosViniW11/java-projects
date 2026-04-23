package br.com.marcos.biblioteca.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private int anoPublicacao;

    @Column(nullable = false)
    private int quantidadeTotal;

    @Column(nullable = false)
    private int quantidadeEstoque;

    @ManyToOne
    @JoinColumn(name = "autor_id",nullable = false)
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;


}
