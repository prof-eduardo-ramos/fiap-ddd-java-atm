package br.com.fiap.bank.atm.infrastructure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.fiap.bank.atm.domain.Dinheiro;
import br.com.fiap.bank.atm.domain.Movimentacao;
import br.com.fiap.bank.atm.domain.TipoMovimentacao;
import br.com.fiap.bank.atm.domain.interfaces.ATMRepository;
import br.com.fiap.bank.atm.infrastructure.database.DatabaseSetup;
import br.com.fiap.bank.atm.infrastructure.repository.MovimentacaoRepository;
import br.com.fiap.bank.atm.infrastructure.repository.jdbc.MovimentacaoRepositoryJdbcImpl;

public class MovimentacaoRepositoryJdbcImplTest {
    @BeforeAll
    public static void setupDatabase() {
        DatabaseSetup.criarTabelas();
    }

    @Test
    public void deveInserirMovimentacaoNoBancoDeDados() {
        ATMRepository<Movimentacao> repository = new MovimentacaoRepositoryJdbcImpl();
        Movimentacao movimentacao = new Movimentacao(LocalDateTime.now(), new Dinheiro("1234.5"), TipoMovimentacao.DEPOSITO);
        repository.adicionar(movimentacao);

        Optional<Movimentacao> novaMovimentacao = repository.buscarPorId(movimentacao.getId());
        assertTrue(novaMovimentacao.isPresent());
    }

    @Test
    public void deveListarTodasAsMovimentacoesDeUmaConta() {
        ATMRepository<Movimentacao> repository = new MovimentacaoRepositoryJdbcImpl();
        List<Movimentacao> movimentacoes = repository.buscarTodas();

        assertFalse(movimentacoes.isEmpty());
    }
}
