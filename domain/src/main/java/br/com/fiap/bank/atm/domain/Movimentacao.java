package br.com.fiap.bank.atm.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Movimentacao extends BaseEntity {

    private LocalDateTime dataHora;
    private TipoMovimentacao tipo;
    private Dinheiro valor;

    @Builder
    private Movimentacao(
            UUID id,
            LocalDate dataCriacao,
            LocalDateTime dataHora,
            Dinheiro valor,
            TipoMovimentacao tipo) {
        super(id, dataCriacao);
        this.dataHora = dataHora;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Movimentacao(LocalDateTime dataHora, Dinheiro valor, TipoMovimentacao tipo) {
        this(null, LocalDate.now(), dataHora, valor, tipo);
    }

}
