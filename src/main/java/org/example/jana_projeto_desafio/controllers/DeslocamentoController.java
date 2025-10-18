package org.example.jana_projeto_desafio.controllers;

import org.example.jana_projeto_desafio.dtos.deslocamento.DeslocamentoResponseDTO;
import org.example.jana_projeto_desafio.dtos.deslocamento.DeslocamentoRetiradaDTO;
import org.example.jana_projeto_desafio.dtos.deslocamento.DeslocamentoRetornoDTO;
import org.example.jana_projeto_desafio.services.DeslocamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("retirada")
    public ResponseEntity<DeslocamentoResponseDTO> createRetirada(@RequestBody DeslocamentoRetiradaDTO deslocamentoRetiradaDTO){
        var retiradaDto = deslocamentoService.registrarRetirada(deslocamentoRetiradaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(retiradaDto);
    }
    @PostMapping("/retorno")
    public ResponseEntity<DeslocamentoResponseDTO> createRetorno(@RequestBody DeslocamentoRetornoDTO deslocamentoRetornoDTO){
        var retornoDto = deslocamentoService.registrarRetorno(deslocamentoRetornoDTO);
        return ResponseEntity.ok(retornoDto);
    }
}
