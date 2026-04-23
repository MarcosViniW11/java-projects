package br.com.marcos.biblioteca.service;

import br.com.marcos.biblioteca.model.Categoria;
import br.com.marcos.biblioteca.repository.CategoriaRepository;
import br.com.marcos.biblioteca.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CategoriaService {

    CategoriaRepository repo;
    public CategoriaService(CategoriaRepository repo) {
        this.repo = repo;
    }
    public List<Categoria> Listar() {
        return repo.findAll();
    }
    public Optional<Categoria> BuscarPorId(Long id){
        return repo.findById(id);
    }
    public Categoria salvar(Categoria categoria){
        return repo.save(categoria);
    }
    public void deletar(Long id){
        repo.deleteById(id);
    }


}
