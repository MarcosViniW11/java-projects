package com.sistema.academia.service;

import com.sistema.academia.model.Cliente;
import com.sistema.academia.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository=clienteRepository;
    }

    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarClientePorId(Long id){
        return clienteRepository.findById(id);
    }

    public Cliente salvar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public void deletarCliente(Long id){
        clienteRepository.deleteById(id);
    }
}
