package com.sistema.estoque_vendas.controller;

import com.sistema.estoque_vendas.model.ItemVenda;
import com.sistema.estoque_vendas.service.ItemVendaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item-venda")
@CrossOrigin(origins = "*")
public class ItemVendaController {

    private final ItemVendaService itemVendaService;

    public ItemVendaController(ItemVendaService itemVendaService) {
        this.itemVendaService = itemVendaService;
    }

    @GetMapping
    public List<ItemVenda> listar() {
        return itemVendaService.listarTodos();
    }

    @GetMapping("/{id}")
    public ItemVenda buscarPorId(@PathVariable Long id) {
        return itemVendaService.buscarPorId(id);
    }

    @PostMapping
    public ItemVenda criar(@RequestBody ItemVenda item) {
        return itemVendaService.salvar(item);
    }

    @PutMapping("/{id}")
    public ItemVenda atualizar(@PathVariable Long id, @RequestBody ItemVenda item) {
        return itemVendaService.atualizar(id, item);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        itemVendaService.deletar(id);
    }

}
