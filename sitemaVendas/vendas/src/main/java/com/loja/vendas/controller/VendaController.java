package com.loja.vendas.controller;

import com.loja.vendas.model.Venda;
import com.loja.vendas.service.VendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/vendas")
@CrossOrigin(origins = "*")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService){
        this.vendaService=vendaService;
    }

    @GetMapping
    public List<Venda> listar(){
        return vendaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscar(@PathVariable Long id){
        return vendaService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Venda> cadastrar(@RequestBody Venda venda){
        return ResponseEntity.ok(vendaService.salvar(venda));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> atualizar(@RequestBody Venda venda,@PathVariable Long id){
        return vendaService.buscarPorId(id).map(existente->{
            venda.setId(id);
            return ResponseEntity.ok(vendaService.salvar(venda));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        vendaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
