package org.example.jana_projeto_desafio.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleRestControllerAdvice  {


    public ResponseEntity<ProblemDetail> handleUserNotFoundException(UserNotFoundException e){
        var exception = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        exception.setTitle("Usuario não encontrado!");
        exception.setDetail(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception);
    }
}
