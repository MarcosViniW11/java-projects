package com.loja.vendas.service;

import com.loja.vendas.model.Produto;
import com.loja.vendas.repository.ProdutoRepository;
import com.loja.vendas.model.ItemVenda;
import com.loja.vendas.repository.ItemVendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Service
public class ItemVendaService {

    private final ItemVendaRepository itemVendaRepository;
    private final ProdutoRepository produtoRepository;

    public ItemVendaService(ItemVendaRepository itemVendaRepository,ProdutoRepository produtoRepository){
        this.itemVendaRepository=itemVendaRepository;
        this.produtoRepository=produtoRepository;
    }

    public List<ItemVenda> listar(){
        return itemVendaRepository.findAll();
    }

    public Optional<ItemVenda> buscarPorId(Long id){
        return itemVendaRepository.findById(id);
    }

    public void processar(ItemVenda itemVenda){

        Produto produto = produtoRepository.findById(
                itemVenda.getProduto().getId()
        ).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (itemVenda.getQuantidadeVendida() > produto.getQuantidadeEstoque()) {
            throw new IllegalStateException("Quantidade de estoque insuficiente");
        }

        itemVenda.setSubTotal(
                itemVenda.getQuantidadeVendida() * produto.getValorUnitario()
        );

        produto.setQuantidadeEstoque(
                produto.getQuantidadeEstoque() - itemVenda.getQuantidadeVendida()
        );

        itemVenda.setProduto(produto);
    }

    public void Deletar(Long id){
        itemVendaRepository.deleteById(id);
    }

}
