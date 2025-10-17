package org.example.jana_projeto_desafio.dtos.deslocamento;

import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;

// DTO para registrar retorno
public record DeslocamentoRetornoDTO(
        @NotNull(message = "ID do deslocamento é obrigatório")
        Integer deslocamentoId,

        @NotNull(message = "Hora de retorno é obrigatória")
        LocalTime horaRetorno
) {}