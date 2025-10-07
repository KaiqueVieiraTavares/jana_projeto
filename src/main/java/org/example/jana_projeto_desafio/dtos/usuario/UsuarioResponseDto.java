package org.example.jana_projeto_desafio.dtos.usuario;

import org.example.jana_projeto_desafio.enums.Perfil;

public record UsuarioResponseDto(
        int matricula,
        String nome,
        String email,
        Perfil perfil
) { }
