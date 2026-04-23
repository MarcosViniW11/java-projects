package br.com.barbearia.sistemaBarbearia.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String telefone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
