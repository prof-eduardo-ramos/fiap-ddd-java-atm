package br.com.fiap.bank.atm;

import java.util.Scanner;
import java.util.UUID;

import br.com.fiap.bank.atm.application.ContaService;
import br.com.fiap.bank.atm.presentation.CadastrarContaAcessoController;
import br.com.fiap.bank.atm.presentation.TerminalBancarioController;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ContaService contaService = new ContaService();
        CadastrarContaAcessoController cadastrarContaAcessoController = new CadastrarContaAcessoController(
                contaService);
        UUID contaId = cadastrarContaAcessoController.executar();

        TerminalBancarioController terminal = new TerminalBancarioController(contaService);
        terminal.executar(contaId);

        scanner.close();
    }

}
