package com.sistema.estoque_vendas.controller;

import com.sistema.estoque_vendas.model.Venda;
import com.sistema.estoque_vendas.service.VendaService;
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

    // Criar venda
    @PostMapping
    public ResponseEntity<Venda> criar(@RequestBody Venda venda) {
        Venda salva = vendaService.salvar(venda);
        return ResponseEntity.ok(salva);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarPorId(@PathVariable Long id) {
        return vendaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Listar todas
    @GetMapping
    public ResponseEntity<List<Venda>> listarTodas() {
        return ResponseEntity.ok(vendaService.listarTodas());
    }

    // Atualizar venda
    @PutMapping("/{id}")
    public ResponseEntity<Venda> atualizar(@PathVariable Long id, @RequestBody Venda venda) {
        return vendaService.atualizar(id, venda)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar venda
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = vendaService.deletar(id);
        return deletado ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
