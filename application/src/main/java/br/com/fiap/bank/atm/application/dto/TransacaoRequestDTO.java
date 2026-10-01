package br.com.fiap.bank.atm.application.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record TransacaoRequestDTO(
        BigDecimal valor,
        String tipoTransacao) {
}
