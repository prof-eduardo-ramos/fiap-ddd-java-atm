package br.com.fiap.bank.atm.infrastructure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.fiap.bank.atm.domain.interfaces.ATMRepository;
import br.com.fiap.bank.atm.domain.Movimentacao;

public class MovimentacaoRepository implements ATMRepository<Movimentacao> {
    // Lista insegura atual do FIAP Bank
    private Set<Movimentacao> movimentacoes = new HashSet<>();

    public void adicionar(Movimentacao entidade) {
        movimentacoes.add(entidade);
    }

    // O jeito antigo (Java 7-): Risco altíssimo de NullPointerException
    public Optional<Movimentacao> buscarPorId(UUID id) {
        return movimentacoes.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
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
                .map(m -> "RECIBO | " + m.getTipo() + " - Valor: R$ " + m.getValor().getValor())
                .collect(Collectors.toList());
    }
}
