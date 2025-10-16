package org.example.jana_projeto_desafio.exceptions.agendamento;

public class AgendamentoJaExisteException extends RuntimeException {
    public AgendamentoJaExisteException(String message) {
        super(message);
    }
}
