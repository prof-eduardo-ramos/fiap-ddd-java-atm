package br.com.fiap.bank.atm.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Movimentacao extends BaseEntity {

    private LocalDateTime dataHora;
    private TipoMovimentacao tipo;

    @Setter
    private Conta conta;
    private Dinheiro valor;

    @Builder
    private Movimentacao(
            UUID id,
            LocalDate dataCriacao,
            Conta conta,
            LocalDateTime dataHora,
            Dinheiro valor,
            TipoMovimentacao tipo) {
        super(id, dataCriacao);
        this.conta = conta;
        this.dataHora = dataHora;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Movimentacao(Conta conta, LocalDateTime dataHora, Dinheiro valor, TipoMovimentacao tipo) {
        this(null, LocalDate.now(), conta, dataHora, valor, tipo);
    }

}
