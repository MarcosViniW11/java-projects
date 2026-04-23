package br.com.barbearia.sistemaBarbearia.controller;

import br.com.barbearia.sistemaBarbearia.dto.CriarAgendamentoDTO;
import br.com.barbearia.sistemaBarbearia.entity.Agendamento;
import br.com.barbearia.sistemaBarbearia.service.AgendamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@CrossOrigin(origins = "*")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService){
        this.agendamentoService=agendamentoService;
    }

    @PostMapping
    public ResponseEntity<Agendamento> cadastrar(@RequestBody CriarAgendamentoDTO dto){
        return ResponseEntity.ok(agendamentoService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> listar(){
        return ResponseEntity.ok(agendamentoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscar(@PathVariable Long id){
        return ResponseEntity.ok(agendamentoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizar(@RequestBody CriarAgendamentoDTO agendamentoDTO, @PathVariable Long id){
        return ResponseEntity.ok(agendamentoService.atualizar(agendamentoDTO,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        agendamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
