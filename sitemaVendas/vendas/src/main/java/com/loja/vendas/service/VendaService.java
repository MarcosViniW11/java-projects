package com.loja.vendas.service;

import com.loja.vendas.model.ItemVenda;
import com.loja.vendas.model.Venda;
import com.loja.vendas.repository.VendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ItemVendaService itemVendaService;

    public VendaService(VendaRepository vendaRepository,ItemVendaService itemVendaService){
        this.vendaRepository=vendaRepository;
        this.itemVendaService=itemVendaService;
    }

    public List<Venda> listar(){
        return vendaRepository.findAll();
    }

    public Optional<Venda> buscarPorId(Long id){
        return vendaRepository.findById(id);
    }

    @Transactional
    public Venda salvar(Venda venda){

        double total=0.0;

        for(ItemVenda item: venda.getListaItens()){
            item.setVenda(venda);
            itemVendaService.processar(item);
            total+=item.getSubTotal();
        }
        venda.setTotal(total);

        return vendaRepository.save(venda);
    }

    public void deletar(Long id){
        vendaRepository.deleteById(id);
    }

}
