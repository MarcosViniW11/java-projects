package br.com.barbearia.sistemaBarbearia.service;


import br.com.barbearia.sistemaBarbearia.dto.ClienteDTO;
import br.com.barbearia.sistemaBarbearia.entity.Cliente;
import br.com.barbearia.sistemaBarbearia.exception.RegraNegocioException;
import br.com.barbearia.sistemaBarbearia.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository repository){
        this.clienteRepository=repository;
    }

    public Cliente criar(ClienteDTO dto){
        Cliente cliente=new Cliente();
        cliente.setNome(dto.nome());
        cliente.setTelefone(dto.telefone());
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarCliente(){
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id){
        return clienteRepository.findById(id).orElseThrow(() -> new RegraNegocioException("Cliente não Encontrado"));
    }

    public Cliente atualizar(ClienteDTO dto,Long id){
        Cliente cliente=clienteRepository.findById(id).orElseThrow(()->new RegraNegocioException("Cliente não encontrado!!"));

        cliente.setNome(dto.nome());
        cliente.setTelefone(dto.telefone());
        return clienteRepository.save(cliente);
    }

    public void deletar(Long id){
         clienteRepository.deleteById(id);
    }

}
