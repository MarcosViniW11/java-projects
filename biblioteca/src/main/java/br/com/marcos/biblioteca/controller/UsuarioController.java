package br.com.marcos.biblioteca.controller;

import br.com.marcos.biblioteca.model.Usuario;
import br.com.marcos.biblioteca.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService usuarioService) {
        this.service = usuarioService;
    }

    @GetMapping
    public List<Usuario> listar(){
        return service.ListarTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> Buscar(@PathVariable Long id){
        return service.BuscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
        Usuario salvar=service.salvar(usuario);
        return ResponseEntity.ok(salvar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable long id,@RequestBody Usuario usuario){
        return service.BuscarPorId(id).map(existente->{usuario.setId(id);return ResponseEntity.ok(service.salvar(usuario));}).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
