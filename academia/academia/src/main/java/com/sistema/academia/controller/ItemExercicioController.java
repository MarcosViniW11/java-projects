package com.sistema.academia.controller;

import com.sistema.academia.model.ItemExercicio;
import com.sistema.academia.service.ItemExercicioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/itens-exercicios")
@CrossOrigin(origins = "*")
public class ItemExercicioController {

    private final ItemExercicioService itemExercicioService;

    public ItemExercicioController(ItemExercicioService itemExercicioService){
        this.itemExercicioService=itemExercicioService;
    }

    @GetMapping
    public List<ItemExercicio> listar(){
        return itemExercicioService.Listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemExercicio> buscarPorId(@PathVariable Long id){
        return itemExercicioService.BuscarPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ItemExercicio> cadastrar(@RequestBody ItemExercicio itemExercicio){
        return ResponseEntity.ok(itemExercicioService.salvar(itemExercicio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemExercicio> atualizar(@RequestBody ItemExercicio itemExercicio,@PathVariable Long id){
        return itemExercicioService.BuscarPorId(id).map(existente->{
            itemExercicio.setId(id);
            return ResponseEntity.ok(itemExercicioService.salvar(itemExercicio));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable  Long id){
        itemExercicioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
