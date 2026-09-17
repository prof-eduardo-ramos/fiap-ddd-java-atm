package br.com.fiap.bank.atm.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter 
@EqualsAndHashCode (of = "valor")
@AllArgsConstructor 
public class Dinheiro {

    private BigDecimal valor;

    // Construtor extra que aceita String para facilitar na hora de criar valores no
    // código,
    // como new Dinheiro("1000.00"), sem precisar criar um BigDecimal na mão.
    public Dinheiro(String valor) {
        this(new BigDecimal(valor));
    }

    // Os métodos abaixo retornam um objeto Dinheiro novo em vez de alterar o atual.
    // Aprendi que isso se chama imutabilidade — evita bugs porque o valor original
    // nunca muda.
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
