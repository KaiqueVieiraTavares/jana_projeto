package org.example.jana_projeto_desafio.dtos.registro;


public record RegistroResponseDTO(
        Integer registroId,
        Integer usuarioId,
        String usuarioNome,
        Integer recursoId,
        String recursoNome,
        Integer localId,
        String localNome,
        Integer agendamentoId,
        Boolean funcional,
        String observacao
) {}
