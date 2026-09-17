package br.com.fiap.bank.atm.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;


@NoArgsConstructor 
@Entity 
@DiscriminatorValue("CONTA_POUPANCA")
public class ContaPoupanca extends Conta {

    // Percentual de rendimento mensal da poupança.
    private static final double RENDIMENTO_MENSAL = 1.1;

    public ContaPoupanca(String numero, String agencia, Cliente cliente, ContaAcesso contaAcesso, Dinheiro saldo) {
        super(numero, agencia, cliente, contaAcesso, saldo, RENDIMENTO_MENSAL);
    }

    // Poupança não tem taxa de saque, então deixei vazio de propósito.
    // Precisei sobrescrever porque o método é abstrato em Conta.
    @Override
    protected void aplicarRegraDeTaxa() {
        // sem taxa no saque
    }

    // Esse método aplica o rendimento mensalmente sobre o saldo atual.
    // Divido por 100 para converter o percentual (1.1%) em fator decimal (0.011).
    public void aplicarTaxaMensal() {
        if (this.status != StatusConta.ATIVA) {
            throw new IllegalStateException("Operação não permitida. A conta está " + this.status + ".");
        }
        BigDecimal fator = BigDecimal.valueOf(RENDIMENTO_MENSAL / 100.0);
        Dinheiro rendimento = new Dinheiro(
                this.saldo.getValor().multiply(fator).setScale(2, RoundingMode.HALF_UP));
        this.saldo = this.saldo.adicionar(rendimento);
        registrarMovimentacao(rendimento, TipoMovimentacao.RENDIMENTO);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
