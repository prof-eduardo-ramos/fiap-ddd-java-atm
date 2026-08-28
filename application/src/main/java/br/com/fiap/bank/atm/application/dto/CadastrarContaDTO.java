package br.com.fiap.bank.atm.application.dto;

import java.math.BigDecimal;

public record CadastrarContaDTO(
        String nomeCliente,
        String cpfCliente,
        String senha,
        BigDecimal saldoInicial) {

}
