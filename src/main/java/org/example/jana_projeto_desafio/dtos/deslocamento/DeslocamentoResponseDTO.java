package org.example.jana_projeto_desafio.dtos.deslocamento;

import java.time.LocalDate;
import java.time.LocalTime;
import org.example.jana_projeto_desafio.enums.Periodo;
import org.example.jana_projeto_desafio.enums.Status;

// DTO de resposta
public record DeslocamentoResponseDTO(
        Integer deslocamentoId,
        LocalDate dataRetirada,
        Periodo periodo,
        LocalTime horaRetirada,
        LocalTime horaRetorno,
        Status status,
        boolean statusEntrega,
        Integer agendamentoId
) {}