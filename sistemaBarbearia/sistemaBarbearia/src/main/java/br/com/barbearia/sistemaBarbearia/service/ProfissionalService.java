package br.com.barbearia.sistemaBarbearia.service;


import br.com.barbearia.sistemaBarbearia.dto.ProfissionalDTO;
import br.com.barbearia.sistemaBarbearia.entity.Profissional;
import br.com.barbearia.sistemaBarbearia.exception.RegraNegocioException;
import br.com.barbearia.sistemaBarbearia.repository.ProfissionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;

    public ProfissionalService(ProfissionalRepository repository){
        this.profissionalRepository=repository;
    }

    public Profissional criar(ProfissionalDTO dto){
        Profissional profissional=new Profissional();
        profissional.setNome(dto.nome());
        profissional.setAtivo(dto.ativo());
        return profissionalRepository.save(profissional);
    }

    public List<Profissional> listar(){
        return profissionalRepository.findAll();
    }

    public Profissional buscar(Long id){
        return profissionalRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Profissional Não Encontrado!!"));
    }

    public Profissional atualiza(ProfissionalDTO profissionalDTO,Long id){
        Profissional profissional=profissionalRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Profissional não Encontrado"));

        profissional.setNome(profissionalDTO.nome());
        profissional.setAtivo(profissionalDTO.ativo());
        return profissionalRepository.save(profissional);
    }

    public void deletar(Long id){
         profissionalRepository.deleteById(id);
    }

}
