package com.projetoTreino.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Exercicio")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "exercicio")
    @JsonIgnore
    private List<ExercicioTreino> treinosOndeAparece;

    

}
