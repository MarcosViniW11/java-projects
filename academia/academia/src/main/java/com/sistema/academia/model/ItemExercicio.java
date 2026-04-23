package com.sistema.academia.model;

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

    private int series;

    private int repeticoes;

    private int tempoDescanco;



}
