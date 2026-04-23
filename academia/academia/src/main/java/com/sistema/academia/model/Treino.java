package com.sistema.academia.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "treinos")
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Cliente cliente;

    private Date dataInicio;

    @OneToMany
    private List<ItemExercicio> listaExercicios;

}
