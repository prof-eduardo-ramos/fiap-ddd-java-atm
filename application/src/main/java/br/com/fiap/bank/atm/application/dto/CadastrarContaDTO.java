package br.com.fiap.bank.atm.application.dto;

import java.math.BigDecimal;

public record CadastrarContaDTO(
                String nomeCliente,
                String cpfCliente,
                String numero,
                String agencia,
                String senha,
                BigDecimal saldoInicial) {

}
