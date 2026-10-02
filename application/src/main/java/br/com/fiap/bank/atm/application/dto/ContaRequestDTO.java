package br.com.fiap.bank.atm.application.dto;

import java.math.BigDecimal;

import br.com.fiap.bank.atm.application.validation.Cpf;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ContaRequestDTO(
                @NotBlank(message = "O nome do cliente é obrigatório.") @Size(min = 2, max = 100, message = "O nome deve conter entre 2 e 100 caracteres.") String nomeCliente,

                @NotBlank(message = "O CPF é obrigatório.") @Size(min = 11, max = 11, message = "O CPF deve conter exatamente 11 dígitos.") @Cpf String cpfCliente,

                @NotBlank(message = "A senha de autorização é obrigatória.") @Size(min = 4, max = 6, message = "A senha deve conter entre 4 e 6 caracteres numéricos.") String senha,

                @NotBlank(message = "O número da conta é obrigatório.") String numero,

                @NotBlank(message = "A agência é obrigatória.") @Pattern(regexp = "\\d{4}", message = "A agência bancária deve conter exatamente 4 dígitos numéricos.") String agencia,

                @NotNull(message = "O depósito inicial não pode ser nulo.") @DecimalMin(value = "20.00", message = "O depósito inicial mínimo para abertura de conta é de R$ 20,00.") BigDecimal saldoInicial) {
}
