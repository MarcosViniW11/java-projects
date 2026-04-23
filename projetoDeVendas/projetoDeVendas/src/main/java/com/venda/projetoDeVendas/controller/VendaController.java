package com.venda.projetoDeVendas.controller;

import com.venda.projetoDeVendas.model.Venda;
import com.venda.projetoDeVendas.service.VendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/vendas")
@CrossOrigin(origins = "*")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @GetMapping
    public List<Venda> listar(){
        return vendaService.buscarVendas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscar(@PathVariable Long id){
        return vendaService.buscarVenda(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Venda> salvar(@RequestBody Venda venda){
        return ResponseEntity.ok(vendaService.salvar(venda));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> atualizar(@PathVariable Long id, @RequestBody Venda venda){
        return vendaService.buscarVenda(id).map(existente->{
            venda.setId(id);
            return ResponseEntity.ok(vendaService.salvar(venda));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        vendaService.excluirVenda(id);
        return ResponseEntity.noContent().build();
    }

}
