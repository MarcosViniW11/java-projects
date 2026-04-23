package com.projetoTreino.controller;

import com.projetoTreino.entity.Exercicio;
import com.projetoTreino.service.ExercicioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercicios")
@CrossOrigin(origins = "*")
public class ExercicioController {

    private final ExercicioService exercicioService;

    public ExercicioController(ExercicioService exercicioService) {
        this.exercicioService = exercicioService;
    }

    @GetMapping
    public ResponseEntity<List<Exercicio>> buscarTodos(){
        return ResponseEntity.ok(exercicioService.listar());
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Exercicio exercicio){
        exercicioService.salvar(exercicio);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercicio> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(exercicioService.buscarPorId(id));
    }

}
