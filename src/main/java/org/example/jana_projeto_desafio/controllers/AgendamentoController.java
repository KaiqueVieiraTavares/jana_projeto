package org.example.jana_projeto_desafio.controllers;

import org.apache.coyote.Response;
import org.example.jana_projeto_desafio.dtos.agendamento.AgendamentoCreateDTO;
import org.example.jana_projeto_desafio.dtos.agendamento.AgendamentoResponseDTO;
import org.example.jana_projeto_desafio.dtos.agendamento.AgendamentoUpdateDTO;
import org.example.jana_projeto_desafio.services.AgendamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {
    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> createAgendamento(@RequestBody AgendamentoCreateDTO agendamentoCreateDTO){
        var agendamentoDto = agendamentoService.createAgendamento(agendamentoCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoDto);
    }
    @GetMapping("/{agendamentoId}")
    public ResponseEntity<AgendamentoResponseDTO> getAgendamento(@PathVariable Integer agendamentoId){
        var agendamentoDto = agendamentoService.getAgendamento(agendamentoId);
        return ResponseEntity.ok(agendamentoDto);
    }
    @GetMapping
    public ResponseEntity<List<AgendamentoResponseDTO>> getAllAgendamentos(){
        var agendamentosDto = agendamentoService.getAllAgendamentosPorUsuario();
        return ResponseEntity.ok(agendamentosDto);
    }
    @DeleteMapping("/{agendamentoId}")
    public ResponseEntity<Void> deleteAgendamento(@PathVariable Integer agendamentoId){
        agendamentoService.deleteAgendamento(agendamentoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/{agendamentoId}")
    public ResponseEntity<AgendamentoResponseDTO> updateAgendamento(@PathVariable Integer agendamentoId,
                                                                    @RequestBody AgendamentoUpdateDTO agendamentoUpdateDTO){
        var agendamentoAtualizado = agendamentoService.updateAgendamento(agendamentoId, agendamentoUpdateDTO);
        return ResponseEntity.ok(agendamentoAtualizado);
    }
}
