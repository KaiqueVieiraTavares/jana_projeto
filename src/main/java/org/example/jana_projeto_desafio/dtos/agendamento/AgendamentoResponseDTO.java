package org.example.jana_projeto_desafio.dtos.agendamento;

import java.time.LocalDate;

public record AgendamentoResponseDTO(
        Integer agendamentoId,
        Integer usuarioId,
        Integer localId,
        Integer recursoId,
        LocalDate dataReservada
) {}
