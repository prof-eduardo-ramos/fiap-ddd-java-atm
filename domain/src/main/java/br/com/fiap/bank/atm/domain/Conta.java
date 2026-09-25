package br.com.fiap.bank.atm.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public Conta(String numero, String agencia, Cliente cliente, ContaAcesso contaAcesso, Dinheiro saldo, Double taxa,
            TipoConta tipo) {
        super();
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
        this.status = StatusConta.ATIVA;
        this.tipo = tipo;
        this.dataAbertura = LocalDate.now();
        this.movimentacoes = new ArrayList<>();
    }

    // Método público chamado de fora. Ele verifica o status e depois delega
    // para os métodos privados sacar() e aplicarRegraDeTaxa().
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
        // Não faz sentido bloquear uma conta que já foi encerrada.
        if (this.status == StatusConta.ENCERRADA) {
            throw new IllegalStateException("Não é possível bloquear uma conta encerrada.");
        }
        this.status = StatusConta.BLOQUEADA;
    }

    public void encerrar() {
        this.status = StatusConta.ENCERRADA;
    }

    // Privado porque ninguém de fora deve chamar diretamente — tem que passar pelo
    // realizarDeposito.
    private void depositar(Dinheiro valor) {
        if (valor == null || valor.menorOuIgualQue(new Dinheiro("0"))) {
            throw new IllegalArgumentException("Valor de depósito deve ser maior que zero.");
        }
        this.saldo = this.saldo.adicionar(valor);
        registrarMovimentacao(valor, TipoMovimentacao.DEPOSITO);
    }

    // Privado pelo mesmo motivo do depositar.
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
