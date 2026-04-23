package br.com.marcos.vendas.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(unique = true)
    private String telefone;

    @Column(unique = true,nullable = false)
    private String cpf;


    private String endereco;

    private Boolean ativo;


}
