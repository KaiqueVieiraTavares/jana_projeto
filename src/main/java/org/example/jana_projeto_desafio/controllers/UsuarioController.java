package org.example.jana_projeto_desafio.controllers;

import org.example.jana_projeto_desafio.dtos.usuario.UsuarioResponseDto;
import org.example.jana_projeto_desafio.dtos.usuario.UsuarioUpdateDto;
import org.example.jana_projeto_desafio.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> getUser(@PathVariable Integer id){
        var usuarioResponseDto = usuarioService.getUser(id);
        return ResponseEntity.ok(usuarioResponseDto);
    }
    @GetMapping("")
    public ResponseEntity<List<UsuarioResponseDto>> getAllUsers(){
        var usuariosResponseDto = usuarioService.getAllUsers();
        return ResponseEntity.ok(usuariosResponseDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        usuarioService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PostMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> updateUser(@PathVariable Integer id,@RequestBody UsuarioUpdateDto usuarioUpdateDto){
        var usuarioUpdate = usuarioService.updateUser(id, usuarioUpdateDto);
        return ResponseEntity.ok(usuarioUpdate);
    }
}
