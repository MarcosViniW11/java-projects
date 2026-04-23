package br.com.barbearia.sistemaBarbearia.controller;

import br.com.barbearia.sistemaBarbearia.dto.CriarProfissionalDTO;
import br.com.barbearia.sistemaBarbearia.dto.ProfissionalResponseDTO;
import br.com.barbearia.sistemaBarbearia.entity.Profissional;
import br.com.barbearia.sistemaBarbearia.service.ProfissionalService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ProfissionalResponseDTO> cadastrar(@RequestBody CriarProfissionalDTO dto){
        Profissional profissional=profissionalService.criar(dto);

        ProfissionalResponseDTO profissionalResponseDTO=new ProfissionalResponseDTO();
        profissionalResponseDTO.setNome(dto.getNome());
        profissionalResponseDTO.setEmail(dto.getEmail());

        return ResponseEntity.status(HttpStatus.CREATED).body(profissionalResponseDTO);
    }


    @GetMapping
    public ResponseEntity<List<Profissional>> listar(){
        return ResponseEntity.ok(profissionalService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profissional> buscar(@PathVariable Long id){
        return ResponseEntity.ok(profissionalService.buscar(id));
    }

    /*
    @PutMapping("/{id}")
    public ResponseEntity<Profissional> atualizar(@RequestBody ProfissionalDTO profissionalDTO,@PathVariable Long id){
        return ResponseEntity.ok(profissionalService.atualiza(profissionalDTO,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        profissionalService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    */

}
