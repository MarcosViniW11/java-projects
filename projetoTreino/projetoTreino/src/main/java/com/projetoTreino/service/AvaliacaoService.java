package com.projetoTreino.service;


import com.projetoTreino.entity.Avaliacao;
import com.projetoTreino.execao.BusinessException;
import com.projetoTreino.repository.AvaliacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
    }

    public void cadastrarAvaliacao(Avaliacao avaliacao){
        avaliacaoRepository.save(avaliacao);
    }

    public List<Avaliacao> listarAvaliacoes(){
        return avaliacaoRepository.findAll();
    }

    public Avaliacao buscarAvaliacaoPorId(Long id){
        return avaliacaoRepository.findById(id).orElseThrow(()->new BusinessException("Avaliação não encontrada"));
    }

}
