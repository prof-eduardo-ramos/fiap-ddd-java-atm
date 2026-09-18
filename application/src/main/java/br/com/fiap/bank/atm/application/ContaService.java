package br.com.fiap.bank.atm.application;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.fiap.bank.atm.application.dto.ContaRequestDTO;
import br.com.fiap.bank.atm.application.dto.ContaResponseDTO;
import br.com.fiap.bank.atm.application.dto.MovimentacaoResponseDTO;
import br.com.fiap.bank.atm.domain.Cliente;
import br.com.fiap.bank.atm.domain.Conta;
import br.com.fiap.bank.atm.domain.ContaAcesso;
import br.com.fiap.bank.atm.domain.Dinheiro;
import br.com.fiap.bank.atm.domain.Movimentacao;
import br.com.fiap.bank.atm.domain.interfaces.ContaRepository;
import br.com.fiap.bank.atm.domain.interfaces.MovimentacaoRepository;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public ContaResponseDTO consultarConta(UUID id) {
        Conta conta = obterContaPorId(id);

        return new ContaResponseDTO(
                conta.getId(),
                conta.getCliente().getNomeCompleto(),
                conta.getCliente().getCpf(),
                conta.getNumero(),
                conta.getAgencia(),
                conta.getSaldo().getValor());

    }

    public ContaResponseDTO cadastrarNovaConta(ContaRequestDTO dto) {
        Cliente cliente = new Cliente(dto.nomeCliente(), dto.cpfCliente());
        ContaAcesso contaAcesso = new ContaAcesso(dto.senha());
        Conta novaConta = ContaFactory.getInstance().criarContaCorrente(dto.numero(), dto.agencia(), cliente,
                contaAcesso, new Dinheiro(dto.saldoInicial()));

        contaRepository.adicionar(novaConta);

        return new ContaResponseDTO(
                novaConta.getId(),
                novaConta.getCliente().getNomeCompleto(),
                novaConta.getCliente().getCpf(),
                novaConta.getNumero(),
                novaConta.getAgencia(),
                novaConta.getSaldo().getValor());

    }

    public List<MovimentacaoResponseDTO> consultarMovimentacoes(UUID id) {
        List<Movimentacao> movimentacoes = contaRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada."))
                .getMovimentacoes();

        return movimentacoes.stream()
                .map(m -> new MovimentacaoResponseDTO(m.getTipo().name(), m.getValor().getValor(), m.getDataHora()))
                .toList();
    }

    public void realizarSaque(UUID id, BigDecimal valor) {
        Conta conta = obterContaPorId(id);
        conta.realizarSaque(new Dinheiro(valor));
        contaRepository.atualizar(conta);
    }

    public void realizarDeposito(UUID id, BigDecimal valor) {
        Conta conta = obterContaPorId(id);
        conta.realizarDeposito(new Dinheiro(valor));
        contaRepository.atualizar(conta);
    }

    public void cancelarConta(UUID id) {
        contaRepository.remover(id);
    }

    private Conta obterContaPorId(UUID id) {
        return contaRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada."));
    }
}
