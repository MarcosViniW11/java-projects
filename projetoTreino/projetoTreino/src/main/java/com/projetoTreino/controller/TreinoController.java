package com.projetoTreino.controller;



import com.projetoTreino.entity.Treino;
import com.projetoTreino.service.TreinoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treinos")
@CrossOrigin(origins = "*")
public class TreinoController {

    private final TreinoService treinoService;

    public TreinoController(TreinoService treinoService) {
        this.treinoService = treinoService;
    }

    @PostMapping
    public ResponseEntity<Treino> cadastrarTreino(@RequestBody Treino treino){
        return ResponseEntity.ok(treinoService.criarTreino(treino));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Treino>> listarTreinoPorIdUser(@PathVariable Long id){
        return ResponseEntity.ok(treinoService.listarTreinoPorUsuario(id));
    }


}
