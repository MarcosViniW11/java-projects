package br.com.marcos.biblioteca.service;

import br.com.marcos.biblioteca.model.Usuario;
import br.com.marcos.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public List<Usuario> ListarTodos(){
        return repo.findAll();
    }

    public Optional<Usuario> BuscarPorId(Long id){
        return repo.findById(id);
    }

    public Usuario salvar(Usuario usuario){
        return repo.save(usuario);
    }

    public void deletar(Long id){
        repo.deleteById(id);
    }

}
