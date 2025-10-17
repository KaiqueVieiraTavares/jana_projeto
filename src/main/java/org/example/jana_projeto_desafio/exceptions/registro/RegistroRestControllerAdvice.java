package org.example.jana_projeto_desafio.exceptions.registro;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RegistroRestControllerAdvice {

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ProblemDetail handleRegistroNaoEncontrado(RegistroNaoEncontradoException e){
        var exception = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        exception.setTitle("Registro não encontrado!");
        return exception;
    }
}
