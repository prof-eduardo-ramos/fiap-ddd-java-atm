package br.com.fiap.bank.atm.presentation;

import br.com.fiap.bank.atm.application.ContaService;
import br.com.fiap.bank.atm.domain.Conta;
import br.com.fiap.bank.atm.domain.Dinheiro;
import br.com.fiap.bank.atm.domain.Movimentacao;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

// Controlador do terminal bancário — é a camada de apresentação do sistema.
// Essa classe só conversa com o usuário via terminal e chama os services para fazer as operações.
// Não coloquei lógica de negócio aqui, só leitura de entrada e exibição de resultado.
public class TerminalBancarioController {

    private Conta conta;
    private Scanner scanner;
    private ContaService contaService;

    // Constante para o separador visual do terminal, evita repetir a string em
    // vários lugares.
    private static final String SEPARADOR = "============================================";

    public TerminalBancarioController(Conta conta, Scanner scanner) {
        this.conta = conta;
        this.scanner = scanner;
        this.contaService = new ContaService(conta);
        this.iniciar();
    }

    // Ponto de entrada do terminal. Primeiro autentica, só depois mostra o menu.
    private void iniciar() {
        System.out.println(SEPARADOR);
        System.out.println("      FIAP BANK - TERMINAL ATM (BETA)      ");
        System.out.println(SEPARADOR);

        if (!autenticar()) {
            System.out.println("Sessão encerrada por segurança. Retire seu cartão.");
            return;
        }

        exibirMenuPrincipal();
    }

    // Método privado porque só o próprio terminal precisa chamar.
    // Controla as tentativas de senha e bloqueia se errar 3 vezes.
    private Boolean autenticar() {
        Integer tentativas = 0;
        Integer maxTentativas = 3;

        while (tentativas < maxTentativas) {
            System.out.print("\nDigite sua senha: ");
            String senha = scanner.nextLine().trim();

            if (conta.getContaAcesso().validarSenha(senha)) {
                System.out.println("Acesso autorizado! Bem-vindo, " + conta.getCliente().obterPrimeiroNome() + "!");
                return Boolean.TRUE;
            }

            tentativas++;
            Integer restantes = maxTentativas - tentativas;

            // Só mostra tentativas restantes se ainda tiver mais chance.
            if (restantes > 0) {
                System.out.println("Senha incorreta. Tentativa(s) restante(s): " + restantes);
            }
        }

        System.out.println("\nConta bloqueada por excesso de tentativas. Contate seu banco.");
        return Boolean.FALSE;
    }

    // Loop principal do terminal — fica rodando até o usuário escolher sair.
    public void exibirMenuPrincipal() {
        Boolean continuar = Boolean.TRUE;

        while (continuar) {
            System.out.println("\n" + SEPARADOR);
            System.out.println("              MENU PRINCIPAL               ");
            System.out.println(SEPARADOR);
            System.out.println("[1] Consultar Saldo");
            System.out.println("[2] Fazer Depósito");
            System.out.println("[3] Fazer Saque");
            System.out.println("[4] Histórico de Movimentações");
            System.out.println("[5] Recibos");
            System.out.println("[6] Sair");
            System.out.println(SEPARADOR);
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    exibirSaldo();
                    break;
                case "2":
                    realizarDeposito();
                    break;
                case "3":
                    realizarSaque();
                    break;
                case "4":
                    exibirMovimentacoes();
                    break;
                case "5":
                    exibirRecibos();
                    break;
                case "6":
                    System.out.println("\nObrigado por utilizar o FIAP Bank ATM. Até logo!");
                    continuar = Boolean.FALSE;
                    break;
                default:
                    System.out.println("Opção inválida. Escolha entre 1 e 5.");
            }
        }
    }

    private void exibirRecibos() {
        System.out.println("\n--- Exibir Recibos ---");
        contaService.gerarRecibos().stream().forEach(System.out::println);
    }

    private void exibirSaldo() {
        System.out.println("\n--- Consulta de Saldo ---");
        System.out.println("Saldo disponível: " + conta.getSaldo());
    }

    private void realizarDeposito() {
        System.out.println("\n--- Fazer Depósito ---");
        System.out.print("Informe o valor do depósito: R$ ");
        // replace(",", ".") para aceitar tanto vírgula quanto ponto como separador
        // decimal.
        String entrada = scanner.nextLine().trim().replace(",", ".");

        try {
            BigDecimal valor = new BigDecimal(entrada);
            conta.realizarDeposito(new Dinheiro(valor));
            System.out.println("Depósito realizado com sucesso!");
            System.out.println("Novo saldo: " + conta.getSaldo());
        } catch (NumberFormatException e) {
            // Captura quando o usuário digita algo que não é número.
            System.out.println("Valor inválido. Digite um número válido.");
        } catch (IllegalArgumentException e) {
            // Captura erros de regra de negócio, como valor negativo ou saldo insuficiente.
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void realizarSaque() {
        System.out.println("\n--- Fazer Saque ---");
        System.out.print("Informe o valor do saque: R$ ");
        String entrada = scanner.nextLine().trim().replace(",", ".");

        try {
            BigDecimal valor = new BigDecimal(entrada);
            conta.realizarSaque(new Dinheiro(valor));
            System.out.println("Saque realizado com sucesso!");
            System.out.println("Novo saldo: " + conta.getSaldo());
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido. Digite um número válido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void exibirMovimentacoes() {
        System.out.println("\n--- Histórico de Movimentações ---");
        List<Movimentacao> movimentacoes = conta.getMovimentacoes();

        if (movimentacoes.isEmpty()) {
            System.out.println("Nenhuma movimentação encontrada.");
            return;
        }

        // Formatei a data no padrão brasileiro para ficar mais fácil de ler.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // printf com %-22s alinha o texto à esquerda e preenche com espaços para a
        // tabela ficar organizada.
        System.out.printf("%-22s | %-12s | %s%n", "Data/Hora", "Tipo", "Valor");
        System.out.println("-------------------------------------------------------");

        movimentacoes.stream()
                .forEach(mov -> System.out.printf("%-22s | %-12s | %s%n",
                        mov.getDataHora().format(formatter),
                        mov.getTipo(),
                        mov.getValor()));

    }
}
