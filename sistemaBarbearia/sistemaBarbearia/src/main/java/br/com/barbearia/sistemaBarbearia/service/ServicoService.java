package br.com.barbearia.sistemaBarbearia.service;


import br.com.barbearia.sistemaBarbearia.dto.ServicoDTO;
import br.com.barbearia.sistemaBarbearia.entity.Servico;
import br.com.barbearia.sistemaBarbearia.exception.RegraNegocioException;
import br.com.barbearia.sistemaBarbearia.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository repository){
        this.servicoRepository=repository;
    }

    public Servico criar(ServicoDTO dto){
      if(dto.duracaoMinutos()<=0){
          throw new RegraNegocioException("Duração Menor ou Igual a zero!!");
      }

      if(dto.preco() < 0){
          throw new RegraNegocioException("Preço incorreto (menor que zero!!)");
      }

      Servico servico=new Servico();
      servico.setNome(dto.nome());
      servico.setDuracaoMinutos(dto.duracaoMinutos());
      servico.setPreco(dto.preco());

      return servicoRepository.save(servico);
    };

    public List<Servico> listar(){
        return servicoRepository.findAll();
    }

    public Servico BuscarPorId(Long id){
        return servicoRepository.findById(id).orElseThrow(()->new RegraNegocioException("Serviço não Encontrado!!"));
    }

    public Servico atualizar(ServicoDTO servicoDTO,Long id){
        Servico servico=servicoRepository.findById(id).orElseThrow(()->new RegraNegocioException("Serviço não Encontrado!!"));

        servico.setNome(servicoDTO.nome());

        if(servicoDTO.duracaoMinutos()<=0){
            throw new RegraNegocioException("Serviço com duração menor ou igual a zero!!");
        }

        servico.setDuracaoMinutos(servicoDTO.duracaoMinutos());

        if(servicoDTO.preco() < 0){
            throw new RegraNegocioException("Preço invalido!!");
        }

        servico.setPreco(servicoDTO.preco());

        return servicoRepository.save(servico);
    }

    public void deletar(Long id){
        servicoRepository.deleteById(id);
    }



}
