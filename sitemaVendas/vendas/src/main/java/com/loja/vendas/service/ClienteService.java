package com.loja.vendas.service;

import com.loja.vendas.model.Cliente;
import com.loja.vendas.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository=clienteRepository;
    }

    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> bucarCliente(Long id){
        return clienteRepository.findById(id);
    }

    public Cliente salvar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public void deletar(Long id){
        clienteRepository.deleteById(id);
    }


}
