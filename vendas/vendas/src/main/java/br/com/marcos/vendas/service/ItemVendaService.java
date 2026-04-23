package br.com.marcos.vendas.service;

import br.com.marcos.vendas.model.ItemVenda;
import br.com.marcos.vendas.model.Produto;
import br.com.marcos.vendas.repository.ItemVendaRepository;
import br.com.marcos.vendas.service.ProdutoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemVendaService {

    private final ItemVendaRepository itemVendaRepository;
    private final ProdutoService produtoService;

    public ItemVendaService(ItemVendaRepository itemVendaRepository,
                            ProdutoService produtoService) {
        this.itemVendaRepository = itemVendaRepository;
        this.produtoService = produtoService;
    }

    public List<ItemVenda> listarTodos() {
        return itemVendaRepository.findAll();
    }

    public Optional<ItemVenda> buscarPorId(Long id) {
        return itemVendaRepository.findById(id);
    }

    public ItemVenda salvar(ItemVenda item) {

        // Busca o produto REAL do banco, com nome, preco, estoque...
        Produto produtoReal = produtoService.buscarPorId(item.getProduto().getId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não informado"));

        // Calcula subtotal com o preço real
        double subtotal = produtoReal.getPrecoUnitario() * item.getQuantidadeVendida();
        item.setSubtotal(subtotal);

        // Associa o produto real
        item.setProduto(produtoReal);

        return itemVendaRepository.save(item);
    }

    public ItemVenda atualizar(ItemVenda itemAtualizado) {
        ItemVenda itemAntigo = itemVendaRepository.findById(itemAtualizado.getId())
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado"));

        Produto produto = produtoService.buscarPorId(itemAtualizado.getProduto().getId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));

        // devolve estoque antigo
        int estoqueRestaurado = produto.getQuantidadeEstoque() + itemAntigo.getQuantidadeVendida();

        // valida novo estoque
        if (estoqueRestaurado < itemAtualizado.getQuantidadeVendida()) {
            throw new IllegalStateException("Estoque insuficiente para atualizar item.");
        }

        // calcula diferença e atualiza estoque corretamente
        produto.setQuantidadeEstoque(estoqueRestaurado - itemAtualizado.getQuantidadeVendida());

        // recalcula subtotal
        double subtotal = produto.getPrecoUnitario() * itemAtualizado.getQuantidadeVendida();
        itemAtualizado.setSubtotal(subtotal);

        // atualiza produto e item
        produtoService.salvar(produto);
        itemAtualizado.setProduto(produto);
        itemAtualizado.setVenda(itemAntigo.getVenda()); // mantém a venda original, caso não venha no JSON

        return itemVendaRepository.save(itemAtualizado);
    }

    public void excluir(Long id) {
        ItemVenda item = itemVendaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item não encontrado"));

        Produto produto = item.getProduto();

        // restaura estoque
        produto.setQuantidadeEstoque(
                produto.getQuantidadeEstoque() + item.getQuantidadeVendida()
        );

        produtoService.salvar(produto);

        itemVendaRepository.deleteById(id);
    }
}
