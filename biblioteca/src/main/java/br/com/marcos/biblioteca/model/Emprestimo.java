package br.com.marcos.biblioteca.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date dataEmprestimo;

    @Column(nullable = false)
    private Date dataPrevistaDevolucao;

    private Date dataDevolucaoReal;

    @Column(nullable = false)
    private StatusEmprestimo status;

    @ManyToOne
    @JoinColumn(name = "usuario_id",nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "livro_id",nullable = false)
    private Livro livro;



}
