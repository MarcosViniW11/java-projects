package br.com.barbearia.sistemaBarbearia.controller;


import br.com.barbearia.sistemaBarbearia.dto.ClienteResponseDTO;
import br.com.barbearia.sistemaBarbearia.dto.CriarClienteDTO;
import br.com.barbearia.sistemaBarbearia.entity.Cliente;
import br.com.barbearia.sistemaBarbearia.service.ClienteService;
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
    public ResponseEntity<ClienteResponseDTO> cadastrar(@RequestBody CriarClienteDTO dto) {

        Cliente cliente = clienteService.criar(dto);

        ClienteResponseDTO response = new ClienteResponseDTO();
        response.setId(cliente.getId());
        response.setNome(cliente.getNome());
        response.setTelefone(cliente.getTelefone());
        response.setEmail(cliente.getUsuario().getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar(){
        return ResponseEntity.ok(clienteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> Buscar(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@RequestBody ClienteDTO clientedto,@PathVariable Long id){
        return ResponseEntity.ok(clienteService.atualizar(clientedto,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }*/

}
