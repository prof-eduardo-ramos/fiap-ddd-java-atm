package br.com.fiap.bank.atm;

import java.util.Scanner;

import br.com.fiap.bank.atm.application.ContaFactory;
import br.com.fiap.bank.atm.application.ContaService;
import br.com.fiap.bank.atm.domain.Cliente;
import br.com.fiap.bank.atm.domain.Conta;
import br.com.fiap.bank.atm.domain.ContaAcesso;
import br.com.fiap.bank.atm.domain.Dinheiro;
import br.com.fiap.bank.atm.presentation.CadastrarContaAcessoController;
import br.com.fiap.bank.atm.presentation.TerminalBancarioController;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CadastrarContaAcessoController cadastrarContaAcessoController = new CadastrarContaAcessoController();
        cadastrarContaAcessoController.iniciar();

        // Cliente cliente = cadastrarContaAcessoController.cadastrarCliente();
        // ContaAcesso contaAcesso =
        // cadastrarContaAcessoController.cadastrarContaAcesso();
        // Dinheiro saldoInicial =
        // cadastrarContaAcessoController.cadastrarSaldoInicial();

        // Conta conta = ContaFactory.getInstance().criarContaCorrente(cliente,
        // contaAcesso, saldoInicial);

        ContaService contaService = new ContaService();
        // contaService.salvarConta();

        TerminalBancarioController terminal = new TerminalBancarioController(conta);
        terminal.iniciar();

        scanner.close();
    }
}
