package br.com.marcos.biblioteca.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true)
    private String email;

    private String telefone;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    private boolean ativo;




}
