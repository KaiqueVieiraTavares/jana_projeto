package org.example.jana_projeto_desafio.controllers;

import org.example.jana_projeto_desafio.dtos.deslocamento.DeslocamentoResponseDTO;
import org.example.jana_projeto_desafio.services.DeslocamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/deslocamentos")
public class DeslocamentoController {
    private final DeslocamentoService deslocamentoService;

    public DeslocamentoController(DeslocamentoService deslocamentoService) {
        this.deslocamentoService = deslocamentoService;
    }
    @GetMapping
    public ResponseEntity<List<DeslocamentoResponseDTO>> getAllDeslocamentos(){
        var deslocamentosDto = deslocamentoService.getAllDeslocamentos();
        return ResponseEntity.ok(deslocamentosDto);
    }
}
