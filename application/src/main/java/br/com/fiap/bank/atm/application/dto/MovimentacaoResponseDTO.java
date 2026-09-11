package br.com.fiap.bank.atm.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MovimentacaoResponseDTO(
        String tipoMovimentacao,
        BigDecimal valor,
        LocalDateTime dataHora) {

}
