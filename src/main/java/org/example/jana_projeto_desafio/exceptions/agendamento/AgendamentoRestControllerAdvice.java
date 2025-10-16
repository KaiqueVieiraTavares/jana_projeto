package org.example.jana_projeto_desafio.exceptions.agendamento;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AgendamentoRestControllerAdvice {
    @ExceptionHandler(AgendamentoJaExisteException.class)
    public ProblemDetail handleAgendamentoJaExisteException(AgendamentoJaExisteException e){
        var exception = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
        exception.setTitle("Já existe um agendamento na data selecionada!");
        return exception;
    }
    @ExceptionHandler(AgendamentoNaoEncontradoException.class)
    public ProblemDetail handleAgendamentoNaoEncontradoException(AgendamentoNaoEncontradoException e){
        var exception = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        exception.setTitle("Agendamento não encotrado!");
        return exception;
    }
}
