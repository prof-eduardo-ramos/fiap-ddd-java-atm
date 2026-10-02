package br.com.fiap.bank.atm.presentation.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ApiErrorResponseDTO(
    LocalDateTime timestamp,
    Integer status,
    String titulo,
    String detalhe,
    String caminho,
    List<CampoErroDTO> errosValidacao
) {

}
