package br.com.barbearia.sistemaBarbearia.controller;


import br.com.barbearia.sistemaBarbearia.dto.ServicoDTO;
import br.com.barbearia.sistemaBarbearia.entity.Servico;
import br.com.barbearia.sistemaBarbearia.service.ServicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos")
@CrossOrigin(origins = "*")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService){
        this.servicoService=servicoService;
    }

    @PostMapping
    public ResponseEntity<Servico> cadastrar(@RequestBody ServicoDTO dto){
        return ResponseEntity.ok(servicoService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<Servico>> listar(){
        return ResponseEntity.ok(servicoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscar(@PathVariable Long id){
        return ResponseEntity.ok(servicoService.BuscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(@RequestBody ServicoDTO servicoDTO,@PathVariable Long id){
        return ResponseEntity.ok(servicoService.atualizar(servicoDTO,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        servicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
