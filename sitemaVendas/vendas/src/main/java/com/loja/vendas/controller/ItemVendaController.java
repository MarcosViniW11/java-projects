package com.loja.vendas.controller;

import com.loja.vendas.model.ItemVenda;
import com.loja.vendas.service.ItemVendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/itemVendas")
@CrossOrigin(origins = "*")
public class ItemVendaController {

    private final ItemVendaService itemVendaService;

    public ItemVendaController(ItemVendaService itemVendaService){
        this.itemVendaService=itemVendaService;
    }

    @GetMapping
    public List<ItemVenda> listar(){
        return itemVendaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemVenda> buscar(@PathVariable Long id){
        return itemVendaService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /*@PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody ItemVenda itemVenda){
        try{
            ItemVenda itemvendaSalvo=itemVendaService.salvar(itemVenda);
            return ResponseEntity.ok(itemvendaSalvo);
        }catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@RequestBody ItemVenda itemVenda,@PathVariable Long id){
        return itemVendaService.buscarPorId(id).map(existente->{
            try{
                itemVenda.setId(id);
                return ResponseEntity.ok(itemVendaService.salvar(itemVenda));
            }catch (IllegalStateException e){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        itemVendaService.buscarPorId(id);
        return ResponseEntity.noContent().build();
    }*/

}
