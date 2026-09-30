package br.com.fiap.bank.atm.domain;

import java.util.function.Function;

public enum TipoConta {
    CONTA_CORRENTE(0.10, valor -> valor.multiplicar(new Dinheiro("0.10"))),
    CONTA_POUPANCA(0.05, valor -> valor.multiplicar(new Dinheiro("0.05")));

    private final Double taxa;
    private final Function<Dinheiro, Dinheiro> regraDeTaxa;

    TipoConta(Double taxa, Function<Dinheiro, Dinheiro> regraDeTaxa) {
        this.taxa = taxa;
        this.regraDeTaxa = regraDeTaxa;
    }

    public Double getTaxa() {
        return taxa;
    }

    public Dinheiro aplicarRegraDeTaxa(Dinheiro valor) {
        return regraDeTaxa.apply(valor);
    }

    public static TipoConta from(String name) {
        return TipoConta.valueOf(name);
    }
}
