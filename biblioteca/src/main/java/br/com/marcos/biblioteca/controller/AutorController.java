package br.com.marcos.biblioteca.controller;

import br.com.marcos.biblioteca.model.Autor;
import br.com.marcos.biblioteca.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autor")
public class AutorController {

    private final AutorService service;

    public AutorController(AutorService autorService) {
        this.service = autorService;
    }

    @GetMapping
    public List<Autor> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscar(@PathVariable Long id) {
        return service.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Autor> inserir(@RequestBody Autor autor) {
        Autor salvar=service.salvar(autor);
        return ResponseEntity.ok().body(salvar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> atualizar(@PathVariable long id,@RequestBody Autor autor) {
        return service.buscarPorId(id).map(existente->{autor.setId(id);return ResponseEntity.ok().body(service.salvar(autor));}).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
