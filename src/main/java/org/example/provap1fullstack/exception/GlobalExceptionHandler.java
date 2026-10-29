package org.example.provap1fullstack.exception;

import org.example.provap1fullstack.dto.ErrorFieldResponse;
import org.example.provap1fullstack.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<ErrorFieldResponse> fields = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                fields.add(new ErrorFieldResponse(error.getField(), error.getDefaultMessage()))
        );

        ErrorResponse errorResponse = new ErrorResponse("Invalid input", fields, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(CategoriaInexistenteException.class)
    public ResponseEntity<ErrorResponse> handleCategoriaInexistente(
            CategoriaInexistenteException ex, HttpServletRequest request) {

        List<ErrorFieldResponse> fields = List.of(
                new ErrorFieldResponse("categoriaId", ex.getMessage())
        );

        ErrorResponse errorResponse = new ErrorResponse("Invalid input", fields, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            ResourceNotFoundException ex, HttpServletRequest request) {

        ErrorResponse errorResponse = new ErrorResponse("Not Found", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
