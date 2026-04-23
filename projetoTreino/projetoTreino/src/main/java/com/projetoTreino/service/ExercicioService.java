package com.projetoTreino.service;

import com.projetoTreino.entity.Exercicio;
import com.projetoTreino.execao.BusinessException;
import com.projetoTreino.repository.ExercicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExercicioService {

    private final ExercicioRepository exercicioRepository;

    public ExercicioService(ExercicioRepository exercicioRepository) {
        this.exercicioRepository = exercicioRepository;
    }

    public void salvar(Exercicio exercicio) {
        exercicioRepository.save(exercicio);
    }

    public List<Exercicio> listar() {
        return exercicioRepository.findAll();
    }

    public Exercicio buscarPorId(Long id) {
        return exercicioRepository.findById(id).orElseThrow(()->new BusinessException("O Exercicio com ID: " + id +
                " Não Existe"));
    }



}
