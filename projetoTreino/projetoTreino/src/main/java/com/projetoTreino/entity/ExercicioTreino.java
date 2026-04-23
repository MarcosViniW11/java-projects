package com.projetoTreino.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "exercicio_treino")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ExercicioTreino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "treino_id")
    @JsonBackReference
    private Treino treino;

    @ManyToOne
    @JoinColumn(name = "exercicio_id")
    private Exercicio exercicio;
    
    private int series;
    private int repeticoes;
    private String carga;
}
