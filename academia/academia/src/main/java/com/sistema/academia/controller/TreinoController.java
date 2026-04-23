package com.sistema.academia.controller;

import com.sistema.academia.model.Treino;
import com.sistema.academia.service.TreinoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treinos")
@CrossOrigin(origins = "*")
public class TreinoController {

    private final TreinoService treinoService;

    public TreinoController(TreinoService treinoService){
        this.treinoService=treinoService;
    }

    @GetMapping
    public List<Treino> listarTreinos(){
        return treinoService.listarTreinos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Treino> buscarPorId(@PathVariable Long id){
        return treinoService.buscarTreinoPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Treino> CadastrarTreino(@RequestBody Treino treino){
        return ResponseEntity.ok(treinoService.salvar(treino));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Treino> AtualizarTreino(@RequestBody Treino treino,@PathVariable Long id){
        return treinoService.buscarTreinoPorId(id).map(existente->{
            treino.setId(id);
            return ResponseEntity.ok(treinoService.salvar(treino));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Treino> deletarTreino(@PathVariable Long id){
        treinoService.deletarTreino(id);
        return ResponseEntity.noContent().build();
    }

}
