package br.com.marcos.biblioteca.controller;

import br.com.marcos.biblioteca.model.Categoria;
import br.com.marcos.biblioteca.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    public final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Categoria> listar() {
        return service.Listar();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscar(@PathVariable Long id) {
        return service.BuscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Categoria> inserir(@RequestBody Categoria categoria) {
        Categoria salvar=service.salvar(categoria);
        return ResponseEntity.ok().body(salvar);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
        return service.BuscarPorId(id).map(existente->{categoria.setId(id);return ResponseEntity.ok().body(service.salvar(categoria));}).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
