package br.com.marcos.vendas.controller;

import br.com.marcos.vendas.model.ItemVenda;
import br.com.marcos.vendas.service.ItemVendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item-venda")
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
    public ResponseEntity<ItemVenda> buscarPorId(@PathVariable Long id) {
        return itemVendaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ItemVenda salvar(@RequestBody ItemVenda itemVenda) {
        return itemVendaService.salvar(itemVenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemVenda> atualizar(@PathVariable Long id, @RequestBody ItemVenda itemVenda) {
        return itemVendaService.buscarPorId(id)
                .map(existente -> {
                    itemVenda.setId(id);
                    ItemVenda atualizado = itemVendaService.atualizar(itemVenda);
                    return ResponseEntity.ok(atualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> remover(@PathVariable Long id) {
        return itemVendaService.buscarPorId(id)
                .map(item -> {
                    itemVendaService.excluir(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
