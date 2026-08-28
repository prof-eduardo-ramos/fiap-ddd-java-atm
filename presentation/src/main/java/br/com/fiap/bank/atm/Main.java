package br.com.fiap.bank.atm;

import java.util.Scanner;

import br.com.fiap.bank.atm.application.ContaService;
import br.com.fiap.bank.atm.domain.Conta;
import br.com.fiap.bank.atm.presentation.CadastrarContaAcessoController;
import br.com.fiap.bank.atm.presentation.TerminalBancarioController;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ContaService contaService = new ContaService();
        CadastrarContaAcessoController cadastrarContaAcessoController = new CadastrarContaAcessoController(
                contaService);
        cadastrarContaAcessoController.iniciar();

        // Cliente cliente = cadastrarContaAcessoController.cadastrarCliente();
        // ContaAcesso contaAcesso =
        // cadastrarContaAcessoController.cadastrarContaAcesso();
        // Dinheiro saldoInicial =
        // cadastrarContaAcessoController.cadastrarSaldoInicial();

        // Conta conta = ContaFactory.getInstance().criarContaCorrente(cliente,
        // contaAcesso, saldoInicial);

        TerminalBancarioController terminal = new TerminalBancarioController(contaService);
        terminal.iniciar();

        scanner.close();
    }

}
