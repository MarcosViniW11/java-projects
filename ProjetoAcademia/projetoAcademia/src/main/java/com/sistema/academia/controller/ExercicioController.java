package com.sistema.academia.controller;

import com.sistema.academia.model.Exercicio;
import com.sistema.academia.service.ExercicioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercicios")
@CrossOrigin(origins = "*")
public class ExercicioController {

    private final ExercicioService exercicioService;

    public ExercicioController(ExercicioService exercicioService){
        this.exercicioService=exercicioService;
    }

    @GetMapping
    public List<Exercicio> listar(){
        return exercicioService.ListarExercicios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exercicio> buscarPorId(@PathVariable Long id){
        return exercicioService.buscarExercicioPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Exercicio> Cadastrar(@RequestBody Exercicio exercicio){
        return ResponseEntity.ok(exercicioService.salvar(exercicio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Exercicio> atualizar(@RequestBody Exercicio exercicio,@PathVariable Long id){
        return exercicioService.buscarExercicioPorId(id).map(existente->{
            exercicio.setId(id);
            return ResponseEntity.ok(exercicioService.salvar(exercicio));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        exercicioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
