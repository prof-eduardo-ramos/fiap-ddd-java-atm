package br.com.fiap.bank.atm.domain;

import java.util.function.Function;

import lombok.Getter;

public enum TipoConta {
    CONTA_CORRENTE(0.10, TipoMovimentacao.TAXA),
    CONTA_POUPANCA(0.05, TipoMovimentacao.RENDIMENTO);

    @Getter
    private final Double taxa;

    @Getter
    private final TipoMovimentacao tipoMovimentacao;
    private final Function<Dinheiro, Dinheiro> regraDeTaxa;

    TipoConta(Double taxa, TipoMovimentacao tipoMovimentacao) {
        this.taxa = taxa;
        this.tipoMovimentacao = tipoMovimentacao;
        this.regraDeTaxa = valor -> valor.multiplicar(new Dinheiro(taxa.toString()));
    }

    public Dinheiro aplicarRegraDeTaxa(Dinheiro valor) {
        return regraDeTaxa.apply(valor);
    }

    public static TipoConta from(String name) {
        return TipoConta.valueOf(name);
    }
}
