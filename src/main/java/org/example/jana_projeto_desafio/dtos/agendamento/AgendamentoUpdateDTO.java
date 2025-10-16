package org.example.jana_projeto_desafio.dtos.agendamento;

import java.time.LocalDate;

public record AgendamentoUpdateDTO(
        Integer agendamentoId,
        Integer localId,
        Integer recursoId,
        LocalDate dataReservada
) {}