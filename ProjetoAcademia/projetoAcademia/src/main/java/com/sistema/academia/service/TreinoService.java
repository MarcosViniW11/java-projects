package com.sistema.academia.service;

import com.sistema.academia.model.ItemExercicio;
import com.sistema.academia.model.Treino;
import com.sistema.academia.repository.TreinoRepository;
import org.springframework.stereotype.Service;

import java.util.*;

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

        if(treino.getCliente()==null){
            throw new IllegalArgumentException("Treino Precisa ter Cliente");
        }

        if(treino.getListaExercicios()==null){
            throw new IllegalArgumentException("Treino Precisa ter ao menos UM exercicio");
        }

        Set<Long> exercicios= new HashSet<>();

        for(ItemExercicio item: treino.getListaExercicios()){

            if(item.getSeries()<=0 || item.getRepeticoes()<=0 || item.getTempoDescanco()<=0){
                throw new IllegalArgumentException("Valores inválidos no exercicio");
            }

            Long exercicioId=item.getExercicio().getId();
            if(!exercicios.add(exercicioId)){
                throw new IllegalArgumentException("Exercicio duplicado no treino");
            }

            item.setTreino(treino);

        };
        List<ItemExercicio> listaItens=treino.getListaExercicios();
        List<ItemExercicio> listaSalvar=new ArrayList<>();
        for (ItemExercicio item:listaItens){
            item.setTreino(treino);
            listaSalvar.add(item);
        };
        treino.setListaExercicios(listaSalvar);
        return treinoRepository.save(treino);
    }

    public void deletarTreino(Long id){
        treinoRepository.deleteById(id);
    }

}
