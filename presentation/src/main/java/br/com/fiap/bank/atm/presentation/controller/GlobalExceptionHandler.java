package br.com.fiap.bank.atm.presentation.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.fiap.bank.atm.presentation.dto.ApiErrorResponseDTO;
import br.com.fiap.bank.atm.presentation.dto.CampoErroDTO;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponseDTO> trataErrosDeValidacao(MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        List<CampoErroDTO> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::toCampoErroDTO)
                .toList();

        ApiErrorResponseDTO erroBody = new ApiErrorResponseDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Violação de Validação de Borda",
                "Um ou mais campos enviados no payload possuem formato ou valor inválido.",
                request.getRequestURI(),
                erros);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroBody);

    }

    private CampoErroDTO toCampoErroDTO(FieldError fieldError) {
        return new CampoErroDTO(
                fieldError.getField(),
                fieldError.getDefaultMessage());
    }

}
