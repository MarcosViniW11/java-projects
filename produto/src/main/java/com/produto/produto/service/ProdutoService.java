package com.produto.produto.service;

import com.produto.produto.model.Produto;
import com.produto.produto.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> ListarProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> BuscarProdutoPorId(long id) {
        return produtoRepository.findById(id);
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void removerProduto(long id) {
        produtoRepository.deleteById(id);
    }


}
