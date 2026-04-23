package br.com.marcos.biblioteca.controller;

import br.com.marcos.biblioteca.model.Emprestimo;
import br.com.marcos.biblioteca.service.EmprestimoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/emprestimo")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping
    public List<Emprestimo> listar() {
        return emprestimoService.Listar();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscar(@PathVariable Long id) {
        return emprestimoService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Emprestimo> inserir(@RequestBody Emprestimo emprestimo) {
        Emprestimo emprestimosave=emprestimoService.salvar(emprestimo);
        return ResponseEntity.ok().body(emprestimosave);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Emprestimo> atualizar(@PathVariable Long id, @RequestBody Emprestimo emprestimo) {
        return emprestimoService.buscarPorId(id).map(existente->{emprestimo.setId(id);return ResponseEntity.ok().body(emprestimoService.salvar(emprestimo));}).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        emprestimoService.remover(id);
        return ResponseEntity.noContent().build();
    }


}
