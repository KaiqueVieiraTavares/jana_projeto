package org.example.jana_projeto_desafio.controllers;

import jakarta.validation.Valid;
import org.example.jana_projeto_desafio.dtos.recurso.RecursoCreateDTO;
import org.example.jana_projeto_desafio.dtos.recurso.RecursoResponseDTO;
import org.example.jana_projeto_desafio.dtos.recurso.RecursoUpdateDTO;
import org.example.jana_projeto_desafio.services.RecursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recursos")
public class RecursoController {
    private final RecursoService recursoService;

    public RecursoController(RecursoService recursoService) {
        this.recursoService = recursoService;
    }
    public ResponseEntity<RecursoResponseDTO> createRecurso(RecursoCreateDTO recursoCreateDTO){
        var recursoCriadoDto = recursoService.createRecurso(recursoCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(recursoCriadoDto);
    }
    @GetMapping("/{recursoId}")
    public ResponseEntity<RecursoResponseDTO> getRecurso(@PathVariable Integer recursoId) {
        var recursoDto = recursoService.getRecurso(recursoId);
        return ResponseEntity.ok(recursoDto);
    }

    @GetMapping()
    public ResponseEntity<List<RecursoResponseDTO>> getAllRecursos() {
        var recursosDto = recursoService.getAllRecursos();
        return ResponseEntity.ok(recursosDto);
    }

    @DeleteMapping("/{recursoId}")
    public ResponseEntity<Void> deleteRecurso(@PathVariable Integer recursoId) {
        recursoService.deleteRecurso(recursoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{recursoId}")
    public ResponseEntity<RecursoResponseDTO> updateRecurso(@PathVariable Integer recursoId, @Valid @RequestBody RecursoUpdateDTO recursoUpdateDTO) {
        var recursoDtoAtualizado = recursoService.updateRecurso(recursoId, recursoUpdateDTO);
        return ResponseEntity.ok(recursoDtoAtualizado);
    }
}
