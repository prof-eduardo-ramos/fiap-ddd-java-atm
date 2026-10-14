package br.com.fiap.bank.atm.presentation.dto.auth;

public record TokenResponseDTO(
        String accessToken,
        String tokenType,
        Long expiresIn,
        String scope) {
}
