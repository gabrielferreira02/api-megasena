package com.gabrielferreira02.megasena.handler;

import com.gabrielferreira02.megasena.dto.ErroResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResponse> illegalArgumentExceptionHandler(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(new ErroResponse(exception.getMessage(), 400));
    }

    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<ErroResponse> transactionSystemExceptionHandler(TransactionSystemException exception) {
        Throwable cause = exception.getRootCause();

        if (cause instanceof ConstraintViolationException constraintViolationException) {
            StringBuilder mensagem = new StringBuilder();
            constraintViolationException.getConstraintViolations().forEach(violation ->
                    mensagem.append(violation.getMessage()).append("; ")
            );

            return ResponseEntity
                    .badRequest()
                    .body(new ErroResponse(mensagem.toString(), 400));
        }

        return ResponseEntity.badRequest().body(new ErroResponse(exception.getMessage(), 500));
    }
}
