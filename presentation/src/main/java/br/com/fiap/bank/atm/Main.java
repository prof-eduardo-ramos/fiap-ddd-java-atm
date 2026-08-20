package br.com.fiap.bank.atm;

import java.util.Scanner;

import br.com.fiap.bank.atm.domain.Conta;
import br.com.fiap.bank.atm.presentation.CadastrarContaAcessoController;
import br.com.fiap.bank.atm.presentation.TerminalBancarioController;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CadastrarContaAcessoController cadastrarContaAcessoController = new CadastrarContaAcessoController(scanner);
        Conta conta = cadastrarContaAcessoController.getConta();
        TerminalBancarioController terminal = new TerminalBancarioController(conta, scanner);

        scanner.close();
    }
}
