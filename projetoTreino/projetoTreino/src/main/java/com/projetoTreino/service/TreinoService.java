package com.projetoTreino.service;

import com.projetoTreino.entity.Exercicio;
import com.projetoTreino.entity.Treino;
import com.projetoTreino.execao.BusinessException;
import com.projetoTreino.repository.ExercicioRepository;
import com.projetoTreino.repository.TreinoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TreinoService {

    private final TreinoRepository treinoRepository;
    private final ExercicioRepository exercicioRepository;

    public TreinoService(TreinoRepository treinoRepository, ExercicioRepository exercicioRepository) {
        this.treinoRepository = treinoRepository;
        this.exercicioRepository = exercicioRepository;
    }

    @Transactional
    public Treino criarTreino(Treino treino) {

        if(treino.getExerciciosRelacionados() != null){
            treino.getExerciciosRelacionados().forEach(item -> {
                item.setTreino(treino);

                Long exercicioId = item.getExercicio().getId();
                Exercicio ex = exercicioRepository.findById(exercicioId).orElseThrow(()->new BusinessException("Exercicio ID: "
                        + exercicioId + " não Existe!"));
                item.setExercicio(ex);
            });
        }

        return treinoRepository.save(treino);
    }

    public List<Treino> listarTreinoPorUsuario(Long usuarioId) {
        if(!treinoRepository.existsTreinoByUsuarioId(usuarioId)){
            throw new BusinessException("Treino Não existente!");
        }
        return treinoRepository.findByUsuarioId(usuarioId).orElseThrow(()->new BusinessException("Lista Não Encontrada!"));
    }

}
