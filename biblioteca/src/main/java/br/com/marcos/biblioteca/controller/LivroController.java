package br.com.marcos.biblioteca.controller;

import br.com.marcos.biblioteca.model.Livro;
import br.com.marcos.biblioteca.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livro")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    @GetMapping
    public List<Livro> listar() {
        return service.buscarTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscar(@PathVariable Long id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Livro> inserir(@RequestBody Livro livro) {
        Livro salvar=service.salvar(livro);
        return ResponseEntity.ok().body(salvar);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable long id,@RequestBody Livro livro) {
        return service.buscarPorId(id).map(existente->{livro.setId(id);return ResponseEntity.ok(service.salvar(livro));}).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
