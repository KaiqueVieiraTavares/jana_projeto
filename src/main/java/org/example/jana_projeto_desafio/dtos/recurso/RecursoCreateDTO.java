package org.example.jana_projeto_desafio.dtos.recurso;

import org.example.jana_projeto_desafio.enums.Tipo;

public record RecursoCreateDTO(
        int codPat,
        Tipo recurso,
        String marca,
        int numero,
        boolean funcional,
        String observacao
) {}
