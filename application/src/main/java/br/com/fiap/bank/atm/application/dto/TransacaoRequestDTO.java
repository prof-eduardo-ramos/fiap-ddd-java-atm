package br.com.fiap.bank.atm.application.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record TransacaoRequestDTO(
        @NotNull(message = "O valor da transação não pode ser nulo.") @DecimalMin(value = "0.01", message = "O valor mínimo de transação é R$ 0,01.") BigDecimal valor,

        @NotBlank(message = "O tipo de conta é obrigatório.") @Pattern(regexp = "DEPOSITO|SAQUE|TRANSFERENCIA", message = "Tipo de conta inválido. Opções válidas: DEPOSITO, SAQUE, TRANSFERENCIA.") String tipoTransacao) {

}
