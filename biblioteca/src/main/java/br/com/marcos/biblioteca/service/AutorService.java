package br.com.marcos.biblioteca.service;

import br.com.marcos.biblioteca.model.Autor;
import br.com.marcos.biblioteca.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class AutorService {

    AutorRepository repo;
    public AutorService(AutorRepository repo) {
        this.repo = repo;
    }

    public List<Autor> listarTodos() {
        return repo.findAll();
    }
    public Optional<Autor> buscarPorId(Long id) {
        return repo.findById(id);
    }

    public Autor salvar(Autor autor) {
        return repo.save(autor);
    }

    public void deletar(long id) {
        repo.deleteById(id);
    }


}
