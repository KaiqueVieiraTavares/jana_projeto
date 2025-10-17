package org.example.jana_projeto_desafio.dtos.registro;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegistroCreateDTO(
        @NotNull(message = "Usuario é obrigatório")
        Integer usuarioId,
        @NotNull(message = "Recurso é obrigatório")
        Integer recursoId,
        Integer localId,
        Integer agendamentoId,
        Boolean funcional,
        @Size(max = 500, message = "Observação pode ter no máximo 500 caracteres")
        String observacao
) {}
