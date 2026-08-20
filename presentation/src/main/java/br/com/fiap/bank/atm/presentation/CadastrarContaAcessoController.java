package br.com.fiap.bank.atm.presentation;

import java.util.Scanner;
import br.com.fiap.bank.atm.application.ContaFactory;
import br.com.fiap.bank.atm.application.ContaService;
import br.com.fiap.bank.atm.domain.Cliente;
import br.com.fiap.bank.atm.domain.Conta;
import br.com.fiap.bank.atm.domain.ContaAcesso;
import br.com.fiap.bank.atm.domain.Dinheiro;

public class CadastrarContaAcessoController {

    private static final String SEPARADOR = "============================================";

    private final ContaService contaService;
    private Scanner scanner;
    private ContaService contaService;
    private Conta conta;

    public CadastrarContaAcessoController(ContaService contaService) {
        this.contaService = contaService;
        this.scanner = new Scanner(System.in);
    }

    public Conta getConta() {
        return this.conta;
    }

    private Conta criarContaCorrente() {
        System.out.println(SEPARADOR);
        System.out.println("     CADASTRO DE CONTA DE ACESSO     ");
        System.out.println(SEPARADOR);

        this.conta = ContaFactory.getInstance().criarContaCorrente(cadastrarCliente(),
                cadastrarContaAcesso(), cadastrarSaldoInicial());
        return this.conta;
    }

    private Cliente cadastrarCliente() {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.nextLine();
        return new Cliente(nome);
    }

    private ContaAcesso cadastrarContaAcesso() {

        System.out.print("Digite a senha de 4 dígitos: ");
        String senha = scanner.nextLine();

        // Valida se a senha tem 4 dígitos, se é composta apenas por números, se não é
        // 0000 e nem 1234 através de Regular Expression
        int quantidadeDeTentativas = 3;

        while (senha.length() != 4 || !senha.matches("\\d{4}") || senha.equals("0000") || senha.equals("1234")) {
            System.out.println("Senha inválida. Digite uma senha de 4 dígitos:");
            senha = scanner.nextLine();
            quantidadeDeTentativas--;

            if (quantidadeDeTentativas <= 0) {
                System.out.println("Senha inválida. Você atingiu o número máximo de tentativas.");
                break;
            }
        }
        return new ContaAcesso(senha);
    }

    private Dinheiro cadastrarSaldoInicial() {
        System.out.print("Digite o saldo inicial: ");
        Dinheiro saldoInicial = new Dinheiro(scanner.nextLine());
        return saldoInicial;
    }

}
