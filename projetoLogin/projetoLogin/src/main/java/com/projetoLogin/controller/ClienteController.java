package com.projetoLogin.controller;

import com.projetoLogin.dto.ClienteDTO;
import com.projetoLogin.dto.ClienteResponseDTO;
import com.projetoLogin.entity.Cliente;
import com.projetoLogin.service.ClienteService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ClienteResponseDTO> cadastrar(@RequestBody ClienteDTO clienteDTO){

        Cliente cliente=clienteService.criar(clienteDTO);

        ClienteResponseDTO response = new ClienteResponseDTO();

        response.setId(cliente.getId());
        response.setNome(cliente.getNome());
        response.setTelefone(cliente.getTelefone());
        response.setEmail(clienteDTO.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar(){
        return ResponseEntity.ok(clienteService.listar());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos(){
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizarPorId(@PathVariable Long id,@RequestBody ClienteDTO clienteDTO){
        return ResponseEntity.ok(clienteService.atualizarPorId(clienteDTO,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id){
        clienteService.deletarClientePorId(id);
        return ResponseEntity.noContent().build();
    }

}
