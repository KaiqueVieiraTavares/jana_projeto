package org.example.jana_projeto_desafio.exceptions.local;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LocalRestControllerAdvice {


    @ExceptionHandler(LocalNaoEncontradoException.class)
    public ProblemDetail handleRecursoNaoEncontrado(LocalNaoEncontradoException e){
        var exception = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        exception.setTitle("Local nao encontrado!");
        exception.setDetail(e.getMessage());
        return exception;
    }
}
