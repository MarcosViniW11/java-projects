package br.com.marcos.vendas.service;

import br.com.marcos.vendas.model.Produto;
import br.com.marcos.vendas.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }
    public Optional<Produto> buscarPorId(Long id){
        return produtoRepository.findById(id);
    }
    public Produto salvar(Produto produto){
        return produtoRepository.save(produto);
    }
    public void remover(Long id){
        produtoRepository.deleteById(id);
    }
    public Produto atualizarEstoque(Long id, int quantidade,boolean adicionar){
        if(quantidade <= 0){
            return null;
        }
        Produto produto = produtoRepository.findById(id).get();

        int quatidadePermitido=produto.getQuantidadeEstoque();
        int soma=produto.getQuantidadeEstoque()+quantidade;
        int subtracao=produto.getQuantidadeEstoque()-quantidade;

        if(adicionar==true){
            produto.setQuantidadeEstoque(soma);
            produtoRepository.save(produto);
        } else if (adicionar==false) {
            if(subtracao>=0){
                produto.setQuantidadeEstoque(subtracao);
                produtoRepository.save(produto);
            }
        }
        return produto;
    }



}
