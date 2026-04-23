package com.sistema.academia.service;

import com.sistema.academia.model.Exercicio;
import com.sistema.academia.repository.ExercicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExercicioService {

    private final ExercicioRepository exercicioRepository;

    public ExercicioService(ExercicioRepository exercicioRepository){
        this.exercicioRepository=exercicioRepository;
    }

    public List<Exercicio> ListarExercicios(){
        return exercicioRepository.findAll();
    }

    public Optional<Exercicio> buscarExercicioPorId(Long id){
        return exercicioRepository.findById(id);
    }

    public Exercicio salvar(Exercicio exercicio){
        return exercicioRepository.save(exercicio);
    }

    public void deletar(Long id){
        exercicioRepository.deleteById(id);
    }

}
