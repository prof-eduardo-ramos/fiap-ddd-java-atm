package br.com.fiap.bank.atm.presentation.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginClientRequestDTO(
        @NotBlank(message = "O clientId é obrigatório.") String clientId,
        @NotBlank(message = "O clientSecret é obrigatório.") String clientSecret) {

}
