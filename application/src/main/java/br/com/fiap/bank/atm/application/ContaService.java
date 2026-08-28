package br.com.fiap.bank.atm.application;

import br.com.fiap.bank.atm.application.dto.CadastrarContaDTO;
import br.com.fiap.bank.atm.application.dto.ContaDTO;
import br.com.fiap.bank.atm.application.dto.MovimentacaoDTO;
import br.com.fiap.bank.atm.domain.Cliente;
import br.com.fiap.bank.atm.domain.Conta;
import br.com.fiap.bank.atm.domain.ContaAcesso;
import br.com.fiap.bank.atm.domain.Dinheiro;
import br.com.fiap.bank.atm.domain.Movimentacao;
import br.com.fiap.bank.atm.domain.interfaces.ClienteRepository;
import br.com.fiap.bank.atm.domain.interfaces.ContaAcessoRepository;
import br.com.fiap.bank.atm.domain.interfaces.ContaRepository;
import br.com.fiap.bank.atm.domain.interfaces.MovimentacaoRepository;
import br.com.fiap.bank.atm.infrastructure.repository.jdbc.ClienteRepositoryJdbcImpl;
import br.com.fiap.bank.atm.infrastructure.repository.jdbc.ContaAcessoRepositoryJdbcImpl;
import br.com.fiap.bank.atm.infrastructure.repository.jdbc.ContaRepositoryJdbcImpl;
import br.com.fiap.bank.atm.infrastructure.repository.jdbc.MovimentacaoRepositoryJdbcImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ContaService {

    private ContaRepository contaRepository;
    private ClienteRepository clienteRepository;
    private ContaAcessoRepository contaAcessoRepository;
    private MovimentacaoRepository movimentacaoRepository;

    public ContaService() {
        this.contaRepository = new ContaRepositoryJdbcImpl();
        this.clienteRepository = new ClienteRepositoryJdbcImpl();
        this.contaAcessoRepository = new ContaAcessoRepositoryJdbcImpl();
        this.movimentacaoRepository = new MovimentacaoRepositoryJdbcImpl();
    }

    public void realizarDeposito(UUID idConta, BigDecimal valor) {
        Optional<Conta> optionalConta = contaRepository.buscarPorId(idConta);

        if (optionalConta.isPresent()) {
            Conta conta = optionalConta.get();
            conta.realizarDeposito(new Dinheiro(valor));
            contaRepository.atualizar(conta);
        }
    }

    public void realizarSaque(UUID idConta, BigDecimal valor) {
        Optional<Conta> optionalConta = contaRepository.buscarPorId(idConta);

        if (optionalConta.isPresent()) {
            Conta conta = optionalConta.get();
            conta.realizarSaque(new Dinheiro(valor));
            contaRepository.atualizar(conta);
        }
    }

    public BigDecimal obterSaldo(UUID idConta) {
        Optional<Conta> optionalConta = contaRepository.buscarPorId(idConta);

        if (optionalConta.isPresent()) {
            Conta conta = optionalConta.get();
            return conta.getSaldo().getValor();
        }

        return BigDecimal.ZERO;
    }

    public List<MovimentacaoDTO> obterMovimentacoes(UUID idConta) {
        List<Movimentacao> movimentacoes = movimentacaoRepository.buscarPorIdConta(idConta);
        return movimentacoes.stream().map(m -> new MovimentacaoDTO(
                idConta,
                m.getDataHora(),
                m.getValor().getValor(),
                m.getTipo().name()))
                .collect(Collectors.toList());
    }

    public String obterNomeCliente(UUID idConta) {
        Optional<Conta> optionalConta = contaRepository.buscarPorId(idConta);

        if (optionalConta.isPresent()) {
            Conta conta = optionalConta.get();
            return conta.getCliente().obterPrimeiroNome();
        }

        return "";
    }

    public UUID salvarConta(CadastrarContaDTO dto) {
        Cliente cliente = new Cliente(dto.nomeCliente(), dto.cpfCliente());
        ContaAcesso contaAcesso = new ContaAcesso(dto.senha());
        Dinheiro saldo = new Dinheiro(dto.saldoInicial());
        Conta conta = ContaFactory.getInstance().criarContaCorrente(dto.numero(), dto.agencia(), cliente, contaAcesso,
                saldo);

        clienteRepository.adicionar(cliente);
        contaRepository.adicionar(conta);
        contaAcessoRepository.adicionar(contaAcesso);

        return conta.getId();
    }

    public Optional<ContaDTO> buscarContaPorId(UUID idConta) {
        Optional<Conta> optionalConta = contaRepository.buscarPorId(idConta);

        if (optionalConta.isPresent()) {
            Conta conta = optionalConta.get();
            return Optional.of(new ContaDTO(
                    conta.getId(),
                    conta.getNumero(),
                    conta.getAgencia(),
                    conta.getSaldo().getValor(),
                    conta.getTaxa(),
                    conta.getStatus().name(),
                    conta.getDataAbertura(),
                    conta.getCliente().obterPrimeiroNome(),
                    conta.getCliente().obterPrimeiroNome(),
                    conta.getContaAcesso().getSenha()));
        }

        return Optional.empty();
    }

}
