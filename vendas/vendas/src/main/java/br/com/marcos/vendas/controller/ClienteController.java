package br.com.marcos.vendas.controller;

import br.com.marcos.vendas.model.Cliente;
import br.com.marcos.vendas.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> listar(){
        return clienteService.listar();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id){
        return clienteService.buscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente){
        Cliente save = clienteService.save(cliente);
        return ResponseEntity.ok().body(save);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id,@RequestBody Cliente cliente){
        return clienteService.buscarPorId(id).map(existe->{cliente.setId(id);return ResponseEntity.ok().body(clienteService.save(cliente));}).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> deletar(@PathVariable Long id){
        clienteService.deletar(id);
        return ResponseEntity.ok().build();
    }

}
