package com.sistema.academia.controller;

import com.sistema.academia.model.Treino;
import com.sistema.academia.service.TreinoService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> CadastrarTreino(@RequestBody Treino treino){
        try{
            Treino salvo=treinoService.salvar(treino);
            return ResponseEntity.ok(salvo);// SE DEU CERTO LANÇA O BODY() COM O .OK(TREINOSALVO)
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());// SE DER ERRO DE SITASE, OU SEJA, FALTANDO ALGUMA IRFORMAÇÃO OU ALGUMA DAS INFORMAÇÕES NÃO ESTEJAM CORRETA
        } catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());// SE DER ERRO DE ESTADO NO CASO ALGUMA REGRA VIOLADA,EXEMPLO: STATUS:CRIADO PARA STATUS:FINALIZADO
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> AtualizarTreino(@RequestBody Treino treino,@PathVariable Long id){
        return treinoService.buscarTreinoPorId(id).map(existente->{
            try{
                treino.setId(id);
                return ResponseEntity.ok(treinoService.salvar(treino));
            }catch (IllegalArgumentException e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }catch (IllegalStateException e){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Treino> deletarTreino(@PathVariable Long id){
        treinoService.deletarTreino(id);
        return ResponseEntity.noContent().build();
    }

}
