package br.com.fiap.bank.atm.domain;

import java.math.BigDecimal;

public record Dinheiro(BigDecimal valor) {

    public Dinheiro(String valor) {
        this(new BigDecimal(valor));
    }

    public Dinheiro adicionar(Dinheiro outro) {
        return new Dinheiro(this.valor.add(outro.valor));
    }

    public Dinheiro subtrair(Dinheiro outro) {
        return new Dinheiro(this.valor.subtract(outro.valor));
    }

    // Uso compareTo em vez de > ou < porque BigDecimal não suporta esses operadores
    // diretamente.
    // compareTo retorna -1, 0 ou 1, então comparo com > 0, == 0 ou < 0.
    public Boolean maiorQue(Dinheiro outro) {
        return this.valor.compareTo(outro.valor) > 0;
    }

    public Boolean maiorOuIgualQue(Dinheiro outro) {
        return this.valor.compareTo(outro.valor) >= 0;
    }

    public Boolean menorOuIgualQue(Dinheiro outro) {
        return this.valor.compareTo(outro.valor) <= 0;
    }

    public Boolean menorQue(Dinheiro outro) {
        return this.valor.compareTo(outro.valor) < 0;
    }

}
