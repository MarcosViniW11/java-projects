package com.sistema.academia.service;

import com.sistema.academia.model.Treino;
import com.sistema.academia.repository.TreinoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreinoService {

    private final TreinoRepository treinoRepository;

    public TreinoService(TreinoRepository treinoRepository){
        this.treinoRepository=treinoRepository;
    }

    public List<Treino> listarTreinos(){
        return treinoRepository.findAll();
    }

    public Optional<Treino> buscarTreinoPorId(Long id){
        return treinoRepository.findById(id);
    }

    public Treino salvar(Treino treino){
        return treinoRepository.save(treino);
    }

    public void deletarTreino(Long id){
        treinoRepository.deleteById(id);
    }

}
