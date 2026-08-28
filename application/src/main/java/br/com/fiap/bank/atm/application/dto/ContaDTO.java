package br.com.fiap.bank.atm.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ContaDTO(
        UUID id,
        String numero,
        String agencia,
        BigDecimal saldo,
        Double taxa,
        String status,
        LocalDate dataAbertura,
        String nomeCliente,
        String cpfCliente,
        String senha) {

}
