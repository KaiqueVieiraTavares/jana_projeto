package org.example.jana_projeto_desafio.exceptions.usuario;

public class EmailJaExisteException extends RuntimeException {
    public EmailJaExisteException(String message) {
        super(message);
    }
}
