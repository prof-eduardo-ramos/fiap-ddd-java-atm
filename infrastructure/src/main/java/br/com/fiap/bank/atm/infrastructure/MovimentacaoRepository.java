package br.com.fiap.bank.atm.infrastructure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.fiap.bank.atm.domain.interfaces.ATMRepository;
import br.com.fiap.bank.atm.domain.Conta;
import br.com.fiap.bank.atm.domain.Movimentacao;
import br.com.fiap.bank.atm.domain.TipoMovimentacao;

public class MovimentacaoRepository implements ATMRepository<Movimentacao> {
     // Lista insegura atual do FIAP Bank
    private Set<Movimentacao> movimentacoes = new HashSet<>();

    public void adicionar(Movimentacao entidade) {
        movimentacoes.add(entidade);
    }

    // O jeito antigo (Java 7-): Risco altíssimo de NullPointerException
    public Optional<Movimentacao> buscarPorId(UUID id) {
        return 
            movimentacoes.stream()
                .filter(movimentacao -> movimentacao.getId().equals(id))
                .findFirst()
        ;
    }

    public void remover(UUID id) {
        movimentacoes.removeIf(movimentacao -> movimentacao.getId().equals(id));
    }

    public List<Movimentacao> buscarTodas() {
        // Convertendo o Set de volta para List para visualização
        return new ArrayList<>(movimentacoes);
    }

    public List<String> gerarRecibosSimples() {
        return movimentacoes.stream()
            .map(movimentacao -> "RECIBO | " + movimentacao.getTipo() + " - Valor: R$ " + movimentacao.getValor().getValor())
            .collect(Collectors.toList())
        ;
    }

    public List<Movimentacao> obterSaques() {
        return movimentacoes.stream()
            .filter(movimentacao -> TipoMovimentacao.SAQUE.equals(movimentacao.getTipo()))
            .collect(Collectors.toList())
        ;
    }

    // Agrupa todas as movimentacoes separadas por conta utilizando a Entidade inteira como chave
    public Map<TipoMovimentacao, List<Movimentacao>> gerarExtratoAgrupado() {
        return movimentacoes.stream()
                .collect(Collectors.groupingBy(Movimentacao::getTipo))
            ;
    }


}
