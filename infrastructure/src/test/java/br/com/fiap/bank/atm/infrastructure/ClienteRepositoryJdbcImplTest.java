package br.com.fiap.bank.atm.infrastructure;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.fiap.bank.atm.domain.Cliente;
import br.com.fiap.bank.atm.domain.interfaces.ATMRepository;
import br.com.fiap.bank.atm.infrastructure.database.DatabaseSetup;
import br.com.fiap.bank.atm.infrastructure.repository.jdbc.ClienteRepositoryJdbcImpl;

public class ClienteRepositoryJdbcImplTest {
    @BeforeAll
    public static void setupDatabase() {
        DatabaseSetup.criarTabelas();
    }

    @Test
    public void deveInserirEBuscarClienteNoBancoDeDados() {
        ATMRepository<Cliente> repository = new ClienteRepositoryJdbcImpl();
        Cliente cliente = new Cliente("Teste Cliente Repository", "12345678901");

        repository.adicionar(cliente);

        assertTrue(repository.buscarPorId(cliente.getId()).isPresent());

    }

    @Test
    public void deveAtualizarDadosDoCliente() {

    }

}
