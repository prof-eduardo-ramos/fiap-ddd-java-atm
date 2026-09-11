package br.com.fiap.bank.atm.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ContaResponseDTO(
        UUID id,
        String nomeCliente,
        String cpfCliente,
        String numero,
        String agencia,
        BigDecimal saldo) {

}
