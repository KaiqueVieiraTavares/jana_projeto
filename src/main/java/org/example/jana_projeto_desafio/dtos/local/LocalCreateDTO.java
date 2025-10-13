package org.example.jana_projeto_desafio.dtos.local;

import org.example.jana_projeto_desafio.enums.Tipo;

public record LocalCreateDTO(
        String local,
        Tipo tipo
) {}