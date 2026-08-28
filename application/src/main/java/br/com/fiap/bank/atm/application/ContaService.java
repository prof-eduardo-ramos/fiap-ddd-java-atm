package br.com.fiap.bank.atm.application;

import br.com.fiap.bank.atm.application.dto.ContaDTO;
import br.com.fiap.bank.atm.domain.Cliente;
import br.com.fiap.bank.atm.domain.Conta;
import br.com.fiap.bank.atm.domain.ContaAcesso;
import br.com.fiap.bank.atm.domain.Dinheiro;
import br.com.fiap.bank.atm.domain.Movimentacao;
import br.com.fiap.bank.atm.domain.interfaces.ATMRepository;
import br.com.fiap.bank.atm.infrastructure.repository.jdbc.ClienteRepositoryJdbcImpl;
import br.com.fiap.bank.atm.infrastructure.repository.jdbc.ContaAcessoRepositoryJdbcImpl;
import br.com.fiap.bank.atm.infrastructure.repository.jdbc.ContaRepositoryJdbcImpl;
import br.com.fiap.bank.atm.infrastructure.repository.jdbc.MovimentacaoRepositoryJdbcImpl;

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
        }

        return "";
    }

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
                    conta.getId(),
                    conta.getNumero(),
                    conta.getAgencia(),
                    conta.getSaldo().getValor(),
                    conta.getTaxa(),
                    conta.getStatus().name(),
                    conta.getDataAbertura(),
                    conta.getCliente().getNomeCompleto(),
                    conta.getCliente().getCpf(),
                    conta.getContaAcesso().getSenha()));
        }
        return contasDTO;
    }

}
