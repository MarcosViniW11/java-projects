package br.com.barbearia.sistemaBarbearia.service;


import br.com.barbearia.sistemaBarbearia.dto.CriarProfissionalDTO;
import br.com.barbearia.sistemaBarbearia.dto.ProfissionalResponseDTO;
import br.com.barbearia.sistemaBarbearia.entity.Profissional;
import br.com.barbearia.sistemaBarbearia.entity.Usuario;
import br.com.barbearia.sistemaBarbearia.enums.Role;
import br.com.barbearia.sistemaBarbearia.exception.RegraNegocioException;
import br.com.barbearia.sistemaBarbearia.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Profissional criar(CriarProfissionalDTO dto) {
        Usuario usuario = usuarioService.criarUsuario(
                dto.getEmail(),
                dto.getSenha(),
                Role.ROLE_BARBEIRO
        );

        Profissional profissional = new Profissional();
        profissional.setNome(dto.getNome());
        profissional.setUsuario(usuario);

        return profissionalRepository.save(profissional);
    }

    public List<Profissional> listar(){
        return profissionalRepository.findAll();
    }

    public Profissional buscar(Long id){
        return profissionalRepository.findById(id).orElseThrow(()->new RegraNegocioException("Não foi possivel encontrar o profissional"));
    }

}
