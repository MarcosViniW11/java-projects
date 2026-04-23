package br.com.marcos.biblioteca.service;

import br.com.marcos.biblioteca.model.Livro;
import br.com.marcos.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private LivroRepository repo;

    public LivroService(LivroRepository repo) {
        this.repo = repo;
    }
    public List<Livro> buscarTodos() {
        return repo.findAll();
    }
    public Optional<Livro> buscarPorId(Long id){
        return repo.findById(id);
    }
    public Optional<Livro> buscarPorTitulo(String titulo){
        return repo.findByTitulo(titulo);
    }
    public Livro salvar(Livro livro){
        return repo.save(livro);
    }
    public void deletar(Long id){
        repo.deleteById(id);
    }

}
