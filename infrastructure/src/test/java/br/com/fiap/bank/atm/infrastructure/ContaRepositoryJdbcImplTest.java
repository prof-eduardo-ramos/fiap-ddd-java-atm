package br.com.fiap.bank.atm.infrastructure;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.fiap.bank.atm.infrastructure.database.DatabaseSetup;

public class ContaRepositoryJdbcImplTest {
    @BeforeAll
    public static void setupDatabase() {
        // Antes de qualquer teste rodar, garantimos que as tabelas existem
        DatabaseSetup.criarTabelas();
    }

    @Test
    public void deveSalvarEBuscarContaNoBancoDeDados() {
        assertTrue(false);
    }
}
