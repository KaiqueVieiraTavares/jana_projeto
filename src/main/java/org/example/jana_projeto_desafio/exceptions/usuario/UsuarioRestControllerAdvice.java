package org.example.jana_projeto_desafio.exceptions.usuario;


import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UsuarioRestControllerAdvice {


    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ProblemDetail handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException e){
        var exception = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        exception.setTitle("Usuario não encontrado!");
        exception.setDetail(e.getMessage());
        return exception;
    }
    @ExceptionHandler(EmailNaoEncontradoException.class)
    public ProblemDetail handleEmailNaoEncontrado(EmailNaoEncontradoException e){
        var exception= ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        exception.setTitle("Email nao encontrado!");
        exception.setDetail(e.getMessage());
        return exception;
    }
    @ExceptionHandler(EmailJaExisteException.class)
    public ProblemDetail handleEmailJaExiste(EmailJaExisteException e){
        var exception = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        exception.setTitle("Email ja existe!");
        exception.setDetail(e.getMessage());
        return exception;
    }
}
