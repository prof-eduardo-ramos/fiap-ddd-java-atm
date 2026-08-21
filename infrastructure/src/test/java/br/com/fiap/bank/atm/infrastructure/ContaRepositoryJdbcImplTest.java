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
    public void deveSalvaContaNoBancoDeDados() {
        Cliente cliente = new Cliente("Teste nome cliente");
        ContaAcesso contaAcesso = new ContaAcesso("1234");
        Dinheiro saldo = new Dinheiro(new BigDecimal("1000.00"));
        Conta conta = new ContaCorrente(cliente, contaAcesso, saldo);

        ContaRepositoryJdbcImpl repository = new ContaRepositoryJdbcImpl();
        repository.adicionar(conta);

        Optional<Conta> contaEncontrada = repository.buscarPorId(conta.getId());

        assertTrue(contaEncontrada.isPresent());
    }
}
