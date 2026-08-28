package br.com.fiap.bank.atm.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record MovimentacaoDTO(
        UUID idConta,
        LocalDateTime dataHora,
        BigDecimal valor,
        String tipo) {

}
