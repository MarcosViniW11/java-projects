package com.projetoTreino.controller;



import com.projetoTreino.entity.Avaliacao;
import com.projetoTreino.service.AvaliacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
@CrossOrigin(origins = "*")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoController(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Avaliacao>> listarAvaliacoes(){
        return ResponseEntity.ok(avaliacaoService.listarAvaliacoes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscarAvaliacaoPorId(@PathVariable Long id){
        return ResponseEntity.ok(avaliacaoService.buscarAvaliacaoPorId(id));
    }

    @PostMapping("/cadastrarAvaliacao")
    public ResponseEntity<Void> cadastrarAvaliacao(@RequestBody Avaliacao avaliacao){
        avaliacaoService.cadastrarAvaliacao(avaliacao);
        return ResponseEntity.ok().build();
    }

}
