package org.example.jana_projeto_desafio.dtos.recurso;


public record RecursoUpdateDTO(
        String marca,
        int numero,
        boolean funcional,
        String observacao
) {}
