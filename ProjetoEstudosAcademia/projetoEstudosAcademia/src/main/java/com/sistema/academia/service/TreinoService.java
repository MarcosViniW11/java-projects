package com.sistema.academia.service;

import com.sistema.academia.model.ItemExercicio;
import com.sistema.academia.model.StatusTreino;
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

        if(treino.getStatus()==null){
            treino.setStatus(StatusTreino.CRIADO);
        }else{
            StatusTreino statusTreinoAtual=treinoRepository.findById(treino.getId()).get().getStatus();
            StatusTreino statusTreinoNovo=treino.getStatus();

            if(statusTreinoAtual==StatusTreino.FINALIZADO){
                throw new IllegalStateException("Treino Finalizado NÃO Pode ser EDITADO!!");//serve para erro de estado como no caso erro de verificação não erro de estrutura!!
            }else if(statusTreinoAtual==StatusTreino.CANCELADO){
                throw new IllegalStateException("Treino Cancelado Não Pode Ser Editado!!");
            }else if(statusTreinoAtual==StatusTreino.CRIADO){
                if(statusTreinoNovo==StatusTreino.FINALIZADO){
                    throw new IllegalStateException("Treino Criado não pode ir para FINALIZADO");
                }else{
                    if(statusTreinoNovo==StatusTreino.EM_ANDAMENTO){
                        treino.setStatus(StatusTreino.EM_ANDAMENTO);
                    }else if(statusTreinoNovo==StatusTreino.CANCELADO){
                        treino.setStatus(StatusTreino.CANCELADO);
                    }
                }
            } else if (statusTreinoAtual==StatusTreino.EM_ANDAMENTO) {
                if(statusTreinoNovo==StatusTreino.FINALIZADO){
                    treino.setStatus(StatusTreino.FINALIZADO);
                }else if(statusTreinoNovo==StatusTreino.CANCELADO){
                    treino.setStatus(StatusTreino.CANCELADO);
                }

            }else{
                throw new IllegalStateException("[ERROR]--Verifique As Regras de edição do Status Do Treino!!");
            }

        }

        if(treino.getCliente()==null){
            throw new IllegalArgumentException("Treino Precisa ter Cliente");//serve para erro de estrutura como no caso um treino não pode tem o campo Cliente vazio
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

    /*public Treino atualizarTreino(Treino treino){

        StatusTreino statusTreinoAntigo=treinoRepository.findById(treino.getId()).get().getStatus();
        StatusTreino statusTreinoNovo=treino.getStatus();

        if(statusTreinoAntigo==StatusTreino.CRIADO && statusTreinoNovo==StatusTreino.EM_ANDAMENTO){
            treino.setStatus(StatusTreino.EM_ANDAMENTO);
        }else{

        }

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

    }*/

    public void deletarTreino(Long id){
        treinoRepository.deleteById(id);
    }

}
