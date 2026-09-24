package br.com.fiap.bank.atm.application;

import br.com.fiap.bank.atm.application.dto.ContaRequestDTO;
import br.com.fiap.bank.atm.application.dto.ContaResponseDTO;
import br.com.fiap.bank.atm.application.dto.MovimentacaoResponseDTO;
import br.com.fiap.bank.atm.domain.Cliente;
import br.com.fiap.bank.atm.domain.Conta;
import br.com.fiap.bank.atm.domain.ContaAcesso;
import br.com.fiap.bank.atm.domain.Dinheiro;
import br.com.fiap.bank.atm.domain.Movimentacao;
import br.com.fiap.bank.atm.domain.interfaces.ContaRepository;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContaService {

        private final ContaRepository contaRepository;

        public ContaResponseDTO consultarConta(UUID id) {
                Conta conta = contaRepository.buscarPorId(id)
                                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada."));
                return new ContaResponseDTO(
                                conta.getId(),
                                conta.getCliente().getNomeCompleto(),
                                conta.getCliente().getCpf(),
                                conta.getNumero(),
                                conta.getAgencia(),
                                conta.getSaldo().valor());
        }

        public List<ContaResponseDTO> buscarTodas() {
                List<Conta> contas = contaRepository.buscarTodas();
                return contas.stream()
                                .map(conta -> new ContaResponseDTO(
                                                conta.getId(),
                                                conta.getCliente().getNomeCompleto(),
                                                conta.getCliente().getCpf(),
                                                conta.getNumero(),
                                                conta.getAgencia(),
                                                conta.getSaldo().valor()))
                                .collect(Collectors.toList());
        }

        public List<MovimentacaoResponseDTO> consultarMovimentacoes(UUID id) {
                List<Movimentacao> movimentacoes = contaRepository.buscarPorId(id).get().getMovimentacoes();

                return movimentacoes.stream()
                                .map(movimentacao -> new MovimentacaoResponseDTO(
                                                movimentacao.getTipo().name(),
                                                movimentacao.getValor().valor(),
                                                movimentacao.getDataHora()))
                                .collect(Collectors.toList());

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
                                novaConta.getSaldo().valor());
        }

        public void realizarSaque(UUID id, BigDecimal valor) {
                Conta conta = contaRepository.buscarPorId(id)
                                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada."));

                conta.realizarSaque(new Dinheiro(valor));
                contaRepository.atualizar(conta);
        }

        public void realizarDeposito(UUID id, BigDecimal valor) {
                Conta conta = contaRepository.buscarPorId(id)
                                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada."));

                conta.realizarDeposito(new Dinheiro(valor));
                contaRepository.atualizar(conta);
        }
}
