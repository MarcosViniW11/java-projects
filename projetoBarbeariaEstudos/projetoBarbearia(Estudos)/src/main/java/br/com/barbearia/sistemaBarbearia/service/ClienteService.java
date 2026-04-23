package br.com.barbearia.sistemaBarbearia.service;


import br.com.barbearia.sistemaBarbearia.dto.CriarClienteDTO;
import br.com.barbearia.sistemaBarbearia.entity.Cliente;
import br.com.barbearia.sistemaBarbearia.entity.Usuario;
import br.com.barbearia.sistemaBarbearia.enums.Role;
import br.com.barbearia.sistemaBarbearia.exception.RegraNegocioException;
import br.com.barbearia.sistemaBarbearia.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {


    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    public Cliente criar(CriarClienteDTO dto) {

        Usuario usuario = usuarioService.criarUsuario(
                dto.getEmail(),
                dto.getSenha(),
                Role.ROLE_CLIENTE
        );

        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setTelefone(dto.getTelefone());
        cliente.setUsuario(usuario);

        return clienteRepository.save(cliente);
    }

    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id){
        return clienteRepository.findById(id).orElseThrow(()-> new RegraNegocioException("Usuario não Encontrado"));
    }

}
