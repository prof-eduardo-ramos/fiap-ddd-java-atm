package br.com.fiap.bank.atm.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Movimentacao extends BaseEntity {

    private LocalDateTime dataHora;
    private TipoMovimentacao tipo;
    private Conta conta;
    private Dinheiro valor;

    public Movimentacao(Conta conta, LocalDateTime dataHora, Dinheiro valor, TipoMovimentacao tipo) {
        this(UUID.randomUUID(), conta, dataHora, valor, tipo);
    }

    @Builder
    private Movimentacao(UUID id, Conta conta, LocalDateTime dataHora, Dinheiro valor, TipoMovimentacao tipo) {
        super(id);
        this.conta = conta;
        this.dataHora = dataHora;
        this.valor = valor;
        this.tipo = tipo;
    }

}
