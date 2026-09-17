package br.com.fiap.bank.atm.domain;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter 
public class Movimentacao extends BaseEntity {

    private LocalDateTime dataHora;
    private TipoMovimentacao tipo;
    private Dinheiro valor;

    public Movimentacao(LocalDateTime dataHora, Dinheiro valor, TipoMovimentacao tipo) {
        super();
        this.dataHora = dataHora;
        this.valor = valor;
        this.tipo = tipo;
    }
}
