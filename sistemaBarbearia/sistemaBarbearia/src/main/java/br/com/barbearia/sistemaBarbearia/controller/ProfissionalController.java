package br.com.barbearia.sistemaBarbearia.controller;

import br.com.barbearia.sistemaBarbearia.dto.ProfissionalDTO;
import br.com.barbearia.sistemaBarbearia.entity.Profissional;
import br.com.barbearia.sistemaBarbearia.service.ProfissionalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissionais")
@CrossOrigin(origins = "*")
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    public ProfissionalController(ProfissionalService profissionalService){
        this.profissionalService=profissionalService;
    }

    @PostMapping
    public ResponseEntity<Profissional> cadastrar(@RequestBody ProfissionalDTO dto){
        return ResponseEntity.ok(profissionalService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<Profissional>> listar(){
        return ResponseEntity.ok(profissionalService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profissional> buscar(@PathVariable Long id){
        return ResponseEntity.ok(profissionalService.buscar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profissional> atualizar(@RequestBody ProfissionalDTO profissionalDTO,@PathVariable Long id){
        return ResponseEntity.ok(profissionalService.atualiza(profissionalDTO,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        profissionalService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
