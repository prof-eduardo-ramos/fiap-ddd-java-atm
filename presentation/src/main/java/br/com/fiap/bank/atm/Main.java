package br.com.fiap.bank.atm;

import java.util.Scanner;
<<<<<<< HEAD

import br.com.fiap.bank.atm.application.ContaFactory;
import br.com.fiap.bank.atm.application.ContaService;
import br.com.fiap.bank.atm.domain.Cliente;
import br.com.fiap.bank.atm.domain.Conta;
import br.com.fiap.bank.atm.domain.ContaAcesso;
import br.com.fiap.bank.atm.domain.Dinheiro;
=======
import java.util.UUID;

import br.com.fiap.bank.atm.application.ContaService;
import br.com.fiap.bank.atm.infrastructure.database.DatabaseSetup;
>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
import br.com.fiap.bank.atm.presentation.CadastrarContaAcessoController;
import br.com.fiap.bank.atm.presentation.TerminalBancarioController;

public class Main {
<<<<<<< HEAD

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
=======
    public static void main(String[] args) {
        DatabaseSetup.criarTabelas();
        Scanner scanner = new Scanner(System.in);
        ContaService contaService = new ContaService();
        CadastrarContaAcessoController cadastrarContaAcessoController = new CadastrarContaAcessoController(
                contaService);
        UUID contaId = cadastrarContaAcessoController.executar();

        TerminalBancarioController terminal = new TerminalBancarioController(contaService);
        terminal.executar(contaId);

        scanner.close();
    }

>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
}
