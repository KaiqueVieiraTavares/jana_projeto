package org.example.jana_projeto_desafio.dtos.recurso;

import org.example.jana_projeto_desafio.enums.Tipo;

public record RecursoResponseDTO(
        Integer recursoId,
        int codPat,
        Tipo recurso,
        String marca,
        int numero,
        boolean funcional,
        String observacao,
        Integer usuarioId,
        String nomeUsuario
) {}