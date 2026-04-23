package com.projetoLogin.service;

import com.projetoLogin.dto.ClienteDTO;
import com.projetoLogin.dto.ClienteResponseDTO;
import com.projetoLogin.entity.Cliente;
import com.projetoLogin.entity.Usuario;
import com.projetoLogin.enums.Role;
import com.projetoLogin.exception.RegraNegocioException;
import com.projetoLogin.repository.ClienteRepository;

import com.projetoLogin.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Cliente criar(ClienteDTO dto){
        Usuario usuario=usuarioService.criarUsuario(
                dto.getEmail(),
                dto.getSenha(),
                Role.ROLE_CLIENTE
        );

        Cliente cliente=new Cliente();

        cliente.setNome(dto.getNome());
        cliente.setTelefone(dto.getTelefone());
        cliente.setUsuario(usuario);

        return clienteRepository.save(cliente);
    }

    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    public List<ClienteResponseDTO> listarTodos(){
        List<Cliente> lista=clienteRepository.findAll();
        List<ClienteResponseDTO> listaDeClientes=new ArrayList<>();

        for(Cliente cliente:lista){
            ClienteResponseDTO clienteResponseDTO=new ClienteResponseDTO();

            clienteResponseDTO.setId(cliente.getId());
            clienteResponseDTO.setNome(cliente.getNome());
            clienteResponseDTO.setTelefone(cliente.getTelefone());
            clienteResponseDTO.setEmail(cliente.getUsuario().getEmail());

            listaDeClientes.add(clienteResponseDTO);

        }

        return listaDeClientes;

    }

    public Cliente buscarPorId(Long id){
        return clienteRepository.findById(id).orElseThrow(()->new RegraNegocioException("Cliente não encontrado!!"));
    }

    @Transactional
    public ClienteResponseDTO atualizarPorId(ClienteDTO cliente, Long id) {

        Cliente clienteBanco = clienteRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado"));

        clienteBanco.setNome(cliente.getNome());
        clienteBanco.setTelefone(cliente.getTelefone());

        // Atualiza o usuário VINCULADO ao cliente
        Usuario usuarioAtualizado = usuarioService.atualizarUsuario(
                clienteBanco.getUsuario().getId(),
                cliente.getEmail(),
                cliente.getSenha()
        );

        clienteBanco.setUsuario(usuarioAtualizado);

        clienteRepository.save(clienteBanco);

        ClienteResponseDTO dto = new ClienteResponseDTO();
        dto.setId(clienteBanco.getId());
        dto.setNome(clienteBanco.getNome());
        dto.setTelefone(clienteBanco.getTelefone());
        dto.setEmail(usuarioAtualizado.getEmail());

        return dto;
    }

    @Transactional
    public void deletarClientePorId(Long id){
        Cliente cliente=clienteRepository.findById(id).orElseThrow(()->new RegraNegocioException("Cliente não encontrado!!"));

        Usuario usuario=cliente.getUsuario();

        clienteRepository.delete(cliente);

        if(usuario!=null){
            usuarioRepository.delete(usuario);
        }

    }

}
