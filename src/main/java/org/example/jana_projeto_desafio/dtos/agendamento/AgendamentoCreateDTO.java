package org.example.jana_projeto_desafio.dtos.agendamento;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record AgendamentoCreateDTO(

        @NotNull(message = "O ID do local é obrigatório.")
        @Positive(message = "O ID do local deve ser positivo.")
        Integer localId,

        @NotNull(message = "O ID do recurso é obrigatório.")
        @Positive(message = "O ID do recurso deve ser positivo.")
        Integer recursoId,

        @NotNull(message = "A data reservada é obrigatória.")
        @Future(message = "A data deve ser futura.")
        LocalDate dataReservada

) {}
