package br.com.barbearia.sistemaBarbearia.controller;


import br.com.barbearia.sistemaBarbearia.dto.ClienteDTO;
import br.com.barbearia.sistemaBarbearia.entity.Cliente;
import br.com.barbearia.sistemaBarbearia.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService){
        this.clienteService=clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody ClienteDTO dto){
        return ResponseEntity.ok(clienteService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar(){
        return ResponseEntity.ok(clienteService.listarCliente());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> Buscar(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@RequestBody ClienteDTO clientedto,@PathVariable Long id){
        return ResponseEntity.ok(clienteService.atualizar(clientedto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
