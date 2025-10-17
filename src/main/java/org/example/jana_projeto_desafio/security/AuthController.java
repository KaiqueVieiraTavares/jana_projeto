package org.example.jana_projeto_desafio.security;

import org.example.jana_projeto_desafio.dtos.usuario.UsuarioResponseDto;
import org.example.jana_projeto_desafio.security.dtos.AuthResponseDto;
import org.example.jana_projeto_desafio.security.dtos.LoginDTO;
import org.example.jana_projeto_desafio.security.dtos.RegisterDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDto> registerUser(@RequestBody RegisterDTO registerDTO){
        var registerDto = authService.registerUser(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerDto);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> loginUser(@RequestBody LoginDTO loginDTO){
        var loginDto = authService.loginUser(loginDTO);
        return ResponseEntity.ok(loginDto);
    }
}
