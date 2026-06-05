package com.eduardodev.banking_system_api.exceptions.globalhandler;

import com.eduardodev.banking_system_api.exceptions.LimiteExcedidoException;
import com.eduardodev.banking_system_api.exceptions.ResourceNotFoundException;
import com.eduardodev.banking_system_api.exceptions.SaldoInsuficienteException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandartError> illegalArgumentExceptionHandler(IllegalArgumentException e) {
        StandartError error = new StandartError(
                Instant.now(),
                400,
                "Bad Request",
                e.getMessage()
        );

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<StandartError> saldoInsuficienteExceptionHandler(SaldoInsuficienteException e) {
        StandartError error = new StandartError(
                Instant.now(),
                400,
                "Saldo Insuficiente",
                e.getMessage()
        );

        return ResponseEntity.badRequest().body(error);
    }
    @ExceptionHandler(LimiteExcedidoException.class)
    public ResponseEntity<StandartError> limiteExcedidoExceptionHandler(LimiteExcedidoException e) {
        StandartError error = new StandartError(
                Instant.now(),
                400,
                "Limite Excedido",
                e.getMessage()
        );

        return ResponseEntity.badRequest().body(error);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandartError> resourceNotFoundExceptionHandler(ResourceNotFoundException e) {
        StandartError error = new StandartError(
                Instant.now(),
                404,
                "Resource Not Found",
                e.getMessage()
        );

        return ResponseEntity.status(404).body(error);
    }
}
