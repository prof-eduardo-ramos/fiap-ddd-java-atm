package br.com.fiap.bank.atm.application;

<<<<<<< HEAD
import br.com.fiap.bank.atm.application.dto.ContaDTO;
=======
import br.com.fiap.bank.atm.application.dto.CadastrarContaDTO;
import br.com.fiap.bank.atm.application.dto.ContaDTO;
import br.com.fiap.bank.atm.application.dto.MovimentacaoDTO;
>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
import br.com.fiap.bank.atm.domain.Cliente;
import br.com.fiap.bank.atm.domain.Conta;
import br.com.fiap.bank.atm.domain.ContaAcesso;
import br.com.fiap.bank.atm.domain.Dinheiro;
import br.com.fiap.bank.atm.domain.Movimentacao;
<<<<<<< HEAD
import br.com.fiap.bank.atm.domain.interfaces.ATMRepository;
=======
import br.com.fiap.bank.atm.domain.interfaces.ClienteRepository;
import br.com.fiap.bank.atm.domain.interfaces.ContaAcessoRepository;
import br.com.fiap.bank.atm.domain.interfaces.ContaRepository;
import br.com.fiap.bank.atm.domain.interfaces.MovimentacaoRepository;
>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
import br.com.fiap.bank.atm.infrastructure.repository.jdbc.ClienteRepositoryJdbcImpl;
import br.com.fiap.bank.atm.infrastructure.repository.jdbc.ContaAcessoRepositoryJdbcImpl;
import br.com.fiap.bank.atm.infrastructure.repository.jdbc.ContaRepositoryJdbcImpl;
import br.com.fiap.bank.atm.infrastructure.repository.jdbc.MovimentacaoRepositoryJdbcImpl;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ContaService {

    private ATMRepository<Conta> contaRepository;
    private ATMRepository<Movimentacao> movimentacaoRepository;
    private ATMRepository<Cliente> clienteRepository;
    private ATMRepository<ContaAcesso> contaAcessoRepository;

    public ContaService() {
        this.contaRepository = new ContaRepositoryJdbcImpl();
        this.movimentacaoRepository = new MovimentacaoRepositoryJdbcImpl();
        this.clienteRepository = new ClienteRepositoryJdbcImpl();
        this.contaAcessoRepository = new ContaAcessoRepositoryJdbcImpl();
    }

    public void realizarDeposito(UUID idConta, Dinheiro valor) {
        Optional<Conta> optional = contaRepository.buscarPorId(idConta);

        if (optional.isPresent()) {
            optional.get().realizarDeposito(valor);
        }
    }

    public void realizarSaque(UUID idConta, Dinheiro valor) {
        Optional<Conta> optional = contaRepository.buscarPorId(idConta);

        if (optional.isPresent()) {
            optional.get().realizarSaque(valor);
        }
    }

    public Dinheiro obterSaldo(UUID idConta) {
        Optional<Conta> optional = contaRepository.buscarPorId(idConta);

        if (optional.isPresent()) {
            return optional.get().getSaldo();
        }
        return new Dinheiro("0");
    }

    public List<Movimentacao> obterMovimentacoes() {
        return movimentacaoRepository.buscarTodas();
    }

    public String obterNomeCliente(UUID idConta) {
        Optional<Conta> optional = contaRepository.buscarPorId(idConta);

        if (optional.isPresent()) {
            return optional.get().getCliente().getNomeCompleto();
=======
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
>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
        }

        return "";
    }

<<<<<<< HEAD
    public void salvarConta(ContaDTO dto) {
        Cliente cliente = new Cliente(dto.nomeCliente(), dto.cpfCliente());
        ContaAcesso contaAcesso = new ContaAcesso(dto.senha());
        Dinheiro saldoInicial = new Dinheiro(dto.saldo());
        Conta conta = ContaFactory.getInstance().criarContaCorrente(cliente, contaAcesso, saldoInicial);

        clienteRepository.adicionar(cliente);
        contaAcessoRepository.adicionar(contaAcesso);
        contaRepository.adicionar(conta);
    }

    public List<ContaDTO> buscarContas() {
        List<Conta> contas = contaRepository.buscarTodas();
        List<ContaDTO> contasDTO = new ArrayList<>();

        for (Conta conta : contas) {
            contasDTO.add(new ContaDTO(
=======
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
>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
                    conta.getId(),
                    conta.getNumero(),
                    conta.getAgencia(),
                    conta.getSaldo().getValor(),
                    conta.getTaxa(),
                    conta.getStatus().name(),
                    conta.getDataAbertura(),
<<<<<<< HEAD
                    conta.getCliente().getNomeCompleto(),
                    conta.getCliente().getCpf(),
                    conta.getContaAcesso().getSenha()));
        }
        return contasDTO;
=======
                    conta.getCliente().obterPrimeiroNome(),
                    conta.getCliente().obterPrimeiroNome(),
                    conta.getContaAcesso().getSenha()));
        }

        return Optional.empty();
>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
    }

}
