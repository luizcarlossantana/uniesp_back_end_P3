package com.alunoonline.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice

public class HandleException {

    @ExceptionHandler(IdNaoEncontadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleExceptionIdNaoEncontrado(IdNaoEncontadoException exception){
        return ResponseEntity.badRequest().body((exception.getMessage()));
    }

    @ExceptionHandler(AtributosNulosException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleExceptionAtribustosNulos(AtributosNulosException exception){
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}

