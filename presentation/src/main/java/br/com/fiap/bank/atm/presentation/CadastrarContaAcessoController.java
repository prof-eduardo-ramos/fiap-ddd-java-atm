package br.com.fiap.bank.atm.presentation;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.UUID;

import br.com.fiap.bank.atm.application.ContaService;
import br.com.fiap.bank.atm.application.dto.CadastrarContaDTO;

public class CadastrarContaAcessoController {

    private static final String SEPARADOR = "============================================";

    private final ContaService contaService;
    private Scanner scanner;

    public CadastrarContaAcessoController(ContaService contaService) {
        this.contaService = contaService;
        this.scanner = new Scanner(System.in);
    }

    public UUID executar() {
        System.out.println(SEPARADOR);
        System.out.println("     CADASTRO DE CONTA DE ACESSO     ");
        System.out.println(SEPARADOR);

        return contaService.salvarConta(
                new CadastrarContaDTO(
                        registrarNomeCliente(),
                        registrarCPFCliente(),
                        registrarSenhaAcesso(),
                        registrarSaldoInicial()));
    }

    private String registrarNomeCliente() {
        System.out.print("Informe o Nome Completo: ");
        return scanner.nextLine();
    }

    private String registrarCPFCliente() {
        System.out.print("Informe o CPF: ");
        return scanner.nextLine();
    }

    private String registrarSenhaAcesso() {

        System.out.print("Digite a senha de 4 dígitos: ");
        String senhaAcesso = scanner.nextLine();

        // Valida se a senha tem 4 dígitos, se é composta apenas por números, se não é
        // 0000 e nem 1234 através de Regular Expression
        int quantidadeDeTentativas = 3;

        while (senhaAcesso.length() != 4 || !senhaAcesso.matches("\\d{4}") || senhaAcesso.equals("0000")
                || senhaAcesso.equals("1234")) {
            System.out.println("Senha inválida. Digite uma senha de 4 dígitos:");
            senhaAcesso = scanner.nextLine();
            quantidadeDeTentativas--;

            if (quantidadeDeTentativas <= 0) {
                System.out.println("Senha inválida. Você atingiu o número máximo de tentativas.");
                break;
            }
        }
        return senhaAcesso;
    }

    private BigDecimal registrarSaldoInicial() {
        System.out.print("Digite o saldo inicial: ");
        return new BigDecimal(scanner.nextLine());
    }

}
