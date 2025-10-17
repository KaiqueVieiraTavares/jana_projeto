package org.example.jana_projeto_desafio.dtos.deslocamento;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import org.example.jana_projeto_desafio.enums.Periodo;

public record DeslocamentoRetiradaDTO(
        @NotNull(message = "Data de retirada é obrigatória")
        LocalDate dataRetirada,

        @NotNull(message = "Período é obrigatório")
        Periodo periodo,

        @NotNull(message = "Hora de retirada é obrigatória")
        LocalTime horaRetirada,

        Integer agendamentoId
) {}
