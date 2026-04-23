package br.com.marcos.vendas.controller;

import br.com.marcos.vendas.model.Venda;
import br.com.marcos.vendas.service.VendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venda")
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @GetMapping
    public List<Venda> listar() {
        return vendaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarPorId(@PathVariable Long id) {
        return vendaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Venda salvar(@RequestBody Venda venda) {
        return vendaService.salvar(venda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venda> atualizar(@PathVariable Long id, @RequestBody Venda venda) {
        return vendaService.buscarPorId(id)
                .map(existente -> {
                    venda.setId(id);
                    Venda atualizado = vendaService.atualizar(venda);
                    return ResponseEntity.ok(atualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        var venda = vendaService.buscarPorId(id);

        if (venda.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        vendaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
