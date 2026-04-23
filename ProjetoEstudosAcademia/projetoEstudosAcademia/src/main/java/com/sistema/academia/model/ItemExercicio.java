package com.sistema.academia.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "itensExercicios")
public class ItemExercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Exercicio exercicio;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "treino_id")
    private Treino treino;

    private int series;

    private int repeticoes;

    private int tempoDescanco;



}
