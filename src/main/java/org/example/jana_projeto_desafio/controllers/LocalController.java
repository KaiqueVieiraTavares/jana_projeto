package org.example.jana_projeto_desafio.controllers;

import org.apache.coyote.Response;
import org.example.jana_projeto_desafio.dtos.local.LocalCreateDTO;
import org.example.jana_projeto_desafio.dtos.local.LocalResponseDTO;
import org.example.jana_projeto_desafio.dtos.local.LocalUpdateDTO;
import org.example.jana_projeto_desafio.services.LocalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LocalController {
    private final LocalService localService;

    public LocalController(LocalService localService) {
        this.localService = localService;
    }
    @PostMapping()
    public ResponseEntity<LocalResponseDTO> createLocal(@RequestBody LocalCreateDTO localCreateDTO){
        var localDto = localService.createLocal(localCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(localDto);
    }
    @GetMapping("/{localId}")
    public ResponseEntity<LocalResponseDTO> getLocal(@PathVariable Integer localId){
        var localDto = localService.getLocal(localId);
        return ResponseEntity.ok(localDto);
    }
    @GetMapping()
    public ResponseEntity<List<LocalResponseDTO>> getAlllocais(){
        var locaisDto = localService.getAllLocais();
        return ResponseEntity.ok(locaisDto);
    }
    @DeleteMapping("/{localId}")
    public ResponseEntity<Void> deleteLocal(@PathVariable Integer localId){
        localService.deleteLocal(localId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{localId}")
    public ResponseEntity<LocalResponseDTO> updateLocal(@PathVariable Integer localId,@RequestBody LocalUpdateDTO localUpdateDTO){
        var localDtoUpdated = localService.updateLocal(localId, localUpdateDTO);
        return ResponseEntity.ok(localDtoUpdated);
    }
}
