package org.example.jana_projeto_desafio.security.dtos;

import org.example.jana_projeto_desafio.enums.Perfil;

public record UsuarioResponseDTO(
        Integer id,
        Integer matricula,
        String nome,
        String email,
        Perfil perfil,
        String localNome
) {}