package com.cadastro.livro.service;


import com.cadastro.livro.model.Livro;
import com.cadastro.livro.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<Livro> ListarLivros() {
        return livroRepository.findAll();
    }

    public Optional<Livro> BuscarPorId(Long id){
        return livroRepository.findById(id);
    }

    public Livro salvar(Livro livro){
        return livroRepository.save(livro);
    }

    public void deletar(Long id){
        livroRepository.deleteById(id);
    }

}
