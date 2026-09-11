package br.com.fiap.bank.atm.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ContaDTO(
<<<<<<< HEAD
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
=======
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

>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
}
