package com.sistema.estoque_vendas.service;

import com.sistema.estoque_vendas.model.ItemVenda;
import com.sistema.estoque_vendas.repository.ItemVendaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemVendaService {

    private final ItemVendaRepository itemVendaRepository;

    public ItemVendaService(ItemVendaRepository itemVendaRepository) {
        this.itemVendaRepository = itemVendaRepository;
    }

    public List<ItemVenda> listarTodos() {
        return itemVendaRepository.findAll();
    }

    public ItemVenda buscarPorId(Long id) {
        return itemVendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ItemVenda não encontrado"));
    }

    public ItemVenda salvar(ItemVenda item) {
        return itemVendaRepository.save(item);
    }

    public ItemVenda atualizar(Long id, ItemVenda novo) {
        ItemVenda item = buscarPorId(id);

        item.setVenda(novo.getVenda());
        item.setProduto(novo.getProduto());
        item.setQuantidade(novo.getQuantidade());
        item.setPrecoUnitario(novo.getPrecoUnitario());

        return itemVendaRepository.save(item);
    }

    public void deletar(Long id) {
        itemVendaRepository.deleteById(id);
    }

}
