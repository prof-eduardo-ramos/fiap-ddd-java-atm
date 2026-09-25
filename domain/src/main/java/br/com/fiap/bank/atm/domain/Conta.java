package br.com.fiap.bank.atm.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Conta extends BaseEntity {

    @Setter
    protected String numero;

    @Setter
    protected String agencia;
    protected Double taxa;
    protected StatusConta status;
    protected TipoConta tipo;
    protected LocalDate dataAbertura;
    protected Dinheiro saldo;
    protected Cliente cliente;
    protected ContaAcesso contaAcesso;
    protected List<Movimentacao> movimentacoes;

    @Builder
    private Conta(
            UUID id,
            LocalDate dataCriacao,
            String numero,
            String agencia,
            Cliente cliente,
            ContaAcesso contaAcesso,
            Dinheiro saldo,
            Double taxa,
            StatusConta status,
            TipoConta tipo,
            LocalDate dataAbertura,
            List<Movimentacao> movimentacoes) {

        super(id, dataCriacao);

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }
        if (contaAcesso == null) {
            throw new IllegalArgumentException("ContaAcesso não pode ser nulo.");
        }
        if (saldo == null) {
            throw new IllegalArgumentException("Saldo não pode ser nulo.");
        }

        this.numero = numero;
        this.agencia = agencia;
        this.cliente = cliente;
        this.contaAcesso = contaAcesso;
        this.saldo = saldo;
        this.taxa = taxa;
        this.tipo = tipo;
        this.status = status;
        this.dataAbertura = dataAbertura;
        this.movimentacoes = movimentacoes;
    }

    public Conta(String numero, String agencia, Cliente cliente, ContaAcesso contaAcesso, Dinheiro saldo, Double taxa,
            TipoConta tipo) {
        this(
                null,
                LocalDate.now(),
                numero,
                agencia,
                cliente,
                contaAcesso,
                saldo,
                taxa,
                StatusConta.ATIVA,
                tipo,
                LocalDate.now(),
                new ArrayList<>());
    }

    public void realizarSaque(Dinheiro valor) {
        if (this.status != StatusConta.ATIVA) {
            throw new IllegalStateException("Operação não permitida. A conta está " + this.status + ".");
        }
        sacar(valor);
        // Chama o método abstrato — cada subclasse decide o que acontece aqui.
        // aplicarRegraDeTaxa();
    }

    public void realizarDeposito(Dinheiro valor) {
        if (this.status != StatusConta.ATIVA) {
            throw new IllegalStateException("Operação não permitida. A conta está " + this.status + ".");
        }
        depositar(valor);
    }

    public void bloquear() {
        if (this.status == StatusConta.ENCERRADA) {
            throw new IllegalStateException("Não é possível bloquear uma conta encerrada.");
        }
        this.status = StatusConta.BLOQUEADA;
    }

    public void encerrar() {
        this.status = StatusConta.ENCERRADA;
    }

    private void depositar(Dinheiro valor) {
        if (valor == null || valor.menorOuIgualQue(new Dinheiro("0"))) {
            throw new IllegalArgumentException("Valor de depósito deve ser maior que zero.");
        }
        this.saldo = this.saldo.adicionar(valor);
        registrarMovimentacao(valor, TipoMovimentacao.DEPOSITO);
    }

    private void sacar(Dinheiro valor) {
        if (valor == null || valor.menorOuIgualQue(new Dinheiro("0"))) {
            throw new IllegalArgumentException("Valor de saque deve ser maior que zero.");
        }
        if (valor.maiorQue(this.saldo)) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar o saque.");
        }
        this.saldo = this.saldo.subtrair(valor);
        registrarMovimentacao(valor, TipoMovimentacao.SAQUE);
    }

    // Método abstrato — força ContaCorrente e ContaPoupanca a implementarem
    // cada uma do seu jeito. Isso é polimorfismo na prática.
    // protected abstract void aplicarRegraDeTaxa();

    // Protected para que as subclasses também possam registrar movimentações,
    // como ContaPoupanca que registra o rendimento mensal.
    protected void registrarMovimentacao(Dinheiro valor, TipoMovimentacao tipo) {
        movimentacoes.add(new Movimentacao(this, LocalDateTime.now(), valor, tipo));
    }

}
