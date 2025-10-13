package org.example.jana_projeto_desafio.security;

import org.example.jana_projeto_desafio.security.dtos.UsuarioResponseDTO;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


}
