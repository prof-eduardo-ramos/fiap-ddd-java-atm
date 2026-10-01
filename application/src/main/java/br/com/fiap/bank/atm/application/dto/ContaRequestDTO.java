package br.com.fiap.bank.atm.application.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ContaRequestDTO(
        String nomeCliente,
        String cpfCliente,
        String senha,
        String numero,
        String agencia,
        BigDecimal saldoInicial) {
}
