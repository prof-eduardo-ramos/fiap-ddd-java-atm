package br.com.fiap.bank.atm.infrastructure;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.fiap.bank.atm.domain.Cliente;
import br.com.fiap.bank.atm.domain.Conta;
import br.com.fiap.bank.atm.domain.ContaAcesso;
import br.com.fiap.bank.atm.domain.ContaCorrente;
import br.com.fiap.bank.atm.domain.Dinheiro;
import br.com.fiap.bank.atm.infrastructure.database.DatabaseSetup;
import br.com.fiap.bank.atm.infrastructure.repository.jdbc.ContaRepositoryJdbcImpl;

public class ContaRepositoryJdbcImplTest {
    @BeforeAll
    public static void setupDatabase() {
        // Antes de qualquer teste rodar, garantimos que as tabelas existem
        DatabaseSetup.criarTabelas();
    }

    @Test
    void deveSalvarEBuscarContaNoBancoDeDados() {
        assertTrue(true);

        // 1. Arrange (Preparação)
        ContaRepositoryJdbcImpl repository = new ContaRepositoryJdbcImpl();
        Cliente cliente = new Cliente("Teste");
        ContaAcesso contaAcesso = new ContaAcesso("1234");
        Dinheiro saldo = new Dinheiro(new BigDecimal("1500.00"));
        Conta novaConta = new ContaCorrente(cliente, contaAcesso, saldo);

        // 2. Act (Ação)
        repository.atualizar(novaConta); // Salva no banco de dados (JDBC)
        Optional<Conta> contaSalva = repository.buscarPorId(novaConta.getId());

        // 3. Assert (Verificação)
        assertTrue(contaSalva.isPresent(), "A conta deveria ser encontrada no banco de dados!");
    }
}
