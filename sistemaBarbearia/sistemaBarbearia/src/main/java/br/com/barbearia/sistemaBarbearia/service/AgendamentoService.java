package br.com.barbearia.sistemaBarbearia.service;


import br.com.barbearia.sistemaBarbearia.dto.CriarAgendamentoDTO;
import br.com.barbearia.sistemaBarbearia.entity.Agendamento;
import br.com.barbearia.sistemaBarbearia.entity.Cliente;
import br.com.barbearia.sistemaBarbearia.entity.Profissional;
import br.com.barbearia.sistemaBarbearia.entity.Servico;
import br.com.barbearia.sistemaBarbearia.enums.StatusAgendamento;
import br.com.barbearia.sistemaBarbearia.exception.RegraNegocioException;
import br.com.barbearia.sistemaBarbearia.repository.AgendamentoRepository;
import br.com.barbearia.sistemaBarbearia.repository.ClienteRepository;
import br.com.barbearia.sistemaBarbearia.repository.ProfissionalRepository;
import br.com.barbearia.sistemaBarbearia.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final ProfissionalRepository profissionalRepository;
    private final ServicoRepository servicoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository,
                              ClienteRepository clienteRepository,
                              ProfissionalRepository profissionalRepository,
                              ServicoRepository servicoRepository){
        this.agendamentoRepository=agendamentoRepository;
        this.clienteRepository=clienteRepository;
        this.profissionalRepository=profissionalRepository;
        this.servicoRepository=servicoRepository;
    }

    public Agendamento criar(CriarAgendamentoDTO dto){
        Cliente cliente=clienteRepository.findById(dto.clienteId()).orElseThrow(()-> new RegraNegocioException("Cliente não encontrado!!"));

        Profissional profissional=profissionalRepository.findById(dto.profissionalId()).orElseThrow(() -> new RegraNegocioException("Profissional não Encontrado!!"));

        if(!profissional.isAtivo()){
            throw new RegraNegocioException("Profissional Inativo!!");
        }

        Servico servico=servicoRepository.findById(dto.servicoId()).orElseThrow(()->new RegraNegocioException("Serviço não Encontrado!!"));

        LocalDateTime inicio=dto.dataHoraInicio();
        LocalDateTime fim=dto.dataHoraInicio().plusMinutes(servico.getDuracaoMinutos());

        if(inicio.isBefore(LocalDateTime.now())){
            throw new RegraNegocioException("Não se pode fazer Um agendamento no passado!!");
        }

        if(agendamentoRepository.existeConflito(profissional,inicio,fim)){
            throw new RegraNegocioException("Horario Indisponivel!!");
        }

        Agendamento agendamento=new Agendamento();

        agendamento.setCliente(cliente);
        agendamento.setProfissional(profissional);
        agendamento.setServico(servico);
        agendamento.setDataHoraInicio(inicio);
        agendamento.setDataHoraFim(fim);
        agendamento.setStatus(StatusAgendamento.AGENDADO);

        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> listar(){
        return agendamentoRepository.findAll();
    }

    public Agendamento buscarPorId(Long id){
        return agendamentoRepository.findById(id).orElseThrow(()->new RegraNegocioException("Agendamento Não Encontrado!!"));
    }

    public Agendamento atualizar(CriarAgendamentoDTO agendamentoDTO,Long id){
        Agendamento agendamento=agendamentoRepository.findById(id).orElseThrow(()->new RegraNegocioException("Agendamento não encontrado!!"));

        Cliente cliente=clienteRepository.findById(agendamentoDTO.clienteId()).orElseThrow(()->new RegraNegocioException("Cliente não encontrado!!"));

        Profissional profissional=profissionalRepository.findById(agendamentoDTO.profissionalId()).orElseThrow(()-> new RegraNegocioException("Proffisional não encontrado!!"));

        if(!profissional.isAtivo()){
            throw new RegraNegocioException("Profissional não esta ativo!!");
        }

        Servico servico=servicoRepository.findById(agendamentoDTO.servicoId()).orElseThrow(()->new RegraNegocioException("serviço não encontrado!!"));

        LocalDateTime inicio=agendamentoDTO.dataHoraInicio();
        LocalDateTime fim=agendamentoDTO.dataHoraInicio().plusMinutes(servico.getDuracaoMinutos());

        if(inicio.isBefore(LocalDateTime.now())){
            throw new RegraNegocioException("não se pode fazer um agendamento no passado!!");
        }

        if(agendamentoRepository.existeConflito(profissional,inicio,fim)){
            throw new RegraNegocioException("horario indisponivel!!");
        }

        agendamento.setCliente(cliente);
        agendamento.setProfissional(profissional);
        agendamento.setServico(servico);
        agendamento.setDataHoraInicio(inicio);
        agendamento.setDataHoraFim(fim);
        agendamento.setStatus(StatusAgendamento.AGENDADO);

        return agendamentoRepository.save(agendamento);

    }

    public void deletar(Long id){
        agendamentoRepository.deleteById(id);
    }


}
