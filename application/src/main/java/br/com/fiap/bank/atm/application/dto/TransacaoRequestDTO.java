package br.com.fiap.bank.atm.application.dto;

import java.math.BigDecimal;

public record TransacaoRequestDTO(BigDecimal valor, String tipoTransacao) {

}
