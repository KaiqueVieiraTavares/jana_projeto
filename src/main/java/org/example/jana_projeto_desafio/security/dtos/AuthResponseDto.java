package org.example.jana_projeto_desafio.security.dtos;


import org.example.jana_projeto_desafio.enums.Perfil;

public record AuthResponseDto(
        Integer id,
        String nome,
        int matricula,
        String email,
        Perfil perfil,
        String token
) {}