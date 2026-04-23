package com.venda.projetoDeVendas.service;

import com.venda.projetoDeVendas.model.Produto;
import com.venda.projetoDeVendas.repository.ProdutoRepository;
import com.venda.projetoDeVendas.model.Venda;
import com.venda.projetoDeVendas.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private ProdutoRepository produtoRepository;

    private VendaRepository vendaRepository;

    public VendaService(ProdutoRepository produtoRepository, VendaRepository vendaRepository) {
        this.produtoRepository = produtoRepository;
        this.vendaRepository = vendaRepository;
    }

    public List<Venda> buscarVendas(){
        return vendaRepository.findAll();
    }

    public Optional<Venda> buscarVenda(Long id){
        return vendaRepository.findById(id);
    }

    public Venda salvar(Venda venda){
        Produto produto = produtoRepository.findById(venda.getProduto().getId()).get();

        if (venda.getQuantidadeASerVendido()>produto.getQuantidadeEstoque()) return null;

        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - venda.getQuantidadeASerVendido());

        venda.setValorTotal(produto.getPrecoUnitario() * venda.getQuantidadeASerVendido());

        venda.setProduto(produto);

        produtoRepository.save(produto);
        return vendaRepository.save(venda);
    }

    public void excluirVenda(Long id){
        Venda venda = vendaRepository.findById(id).get();
        Produto produto = produtoRepository.findById(venda.getProduto().getId()).get();

        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + venda.getQuantidadeASerVendido());

        produtoRepository.save(produto);
        vendaRepository.delete(venda);
    }



}
