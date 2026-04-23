package com.sistema.estoque_vendas.service;

import com.sistema.estoque_vendas.model.Venda;
import com.sistema.estoque_vendas.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;

    public VendaService(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    // Criar venda
    public Venda salvar(Venda venda) {

        // Ajustar itens
        if (venda.getItens() != null) {
            venda.getItens().forEach(item -> {
                item.setVenda(venda);
            });
        }

        return vendaRepository.save(venda);
    }


    // Buscar por ID
    public Optional<Venda> buscarPorId(Long id) {
        return vendaRepository.findById(id);
    }

    // Listar todas
    public List<Venda> listarTodas() {
        return vendaRepository.findAll();
    }

    // Atualizar venda
    public Optional<Venda> atualizar(Long id, Venda vendaAtualizada) {
        return vendaRepository.findById(id).map(venda -> {
            venda.setCliente(vendaAtualizada.getCliente());
            venda.setDataVenda(vendaAtualizada.getDataVenda());
            venda.setTotal(vendaAtualizada.getTotal());
            return vendaRepository.save(venda);
        });
    }

    // Deletar
    public boolean deletar(Long id) {
        return vendaRepository.findById(id).map(venda -> {
            vendaRepository.delete(venda);
            return true;
        }).orElse(false);
    }
}
