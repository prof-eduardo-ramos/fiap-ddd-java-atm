package br.com.fiap.bank.atm.presentation.dto.form;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // Construtor vazio obrigatório para o Spring MVC (Data Binding)
@AllArgsConstructor
public class OperacaoFormDTO {

    private UUID idConta;
    private BigDecimal valor;
    private String tipoOperacao;

}
