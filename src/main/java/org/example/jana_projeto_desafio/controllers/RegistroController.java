package org.example.jana_projeto_desafio.controllers;

import org.example.jana_projeto_desafio.dtos.registro.RegistroResponseDTO;
import org.example.jana_projeto_desafio.services.RegistroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registros")
public class RegistroController {
    private final RegistroService registroService;

    public RegistroController(RegistroService registroService) {
        this.registroService = registroService;
    }

    @GetMapping
    public ResponseEntity<List<RegistroResponseDTO>> getAllRegistros(){
        var registrosDto = registroService.getAllRegistros();
        return ResponseEntity.ok(registrosDto);
    }

    @DeleteMapping("/{registroId}")
    public ResponseEntity<Void> deleteRegistro(@PathVariable Integer registroId){
        registroService.deleteRegistro(registroId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
