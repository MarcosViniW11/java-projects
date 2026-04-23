package com.projetoTreino.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "avaliacoes")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal peso;
    private BigDecimal altura;

    // 2. O mappedBy agora aponta para o nome exato do campo na classe Usuario
    @OneToOne(mappedBy = "avaliacao")
    @JsonIgnore
    private Usuario usuario;

}
