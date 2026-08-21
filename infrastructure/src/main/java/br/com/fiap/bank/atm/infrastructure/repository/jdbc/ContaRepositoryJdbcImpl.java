package br.com.fiap.bank.atm.infrastructure.repository.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.fiap.bank.atm.domain.Cliente;
import br.com.fiap.bank.atm.domain.Conta;
import br.com.fiap.bank.atm.domain.ContaAcesso;
import br.com.fiap.bank.atm.domain.ContaCorrente;
import br.com.fiap.bank.atm.domain.Dinheiro;
import br.com.fiap.bank.atm.domain.interfaces.ATMRepository;
import br.com.fiap.bank.atm.infrastructure.database.DatabaseConnectionFactory;

public class ContaRepositoryJdbcImpl implements ATMRepository<Conta> {

    @Override
    public void adicionar(Conta conta) {
        String sql = "INSERT INTO tb_conta (id, cliente_id, agencia, numero, saldo, status) VALUES " +
                "('" + conta.getId() + "', '" + conta.getCliente().getId() + "', '" + conta.getAgencia() + "', '"
                + conta.getNumero() + "', " + conta.getSaldo().getValor().doubleValue() + ", '"
                + conta.getStatus().name() + "')";

        try (Connection conn = DatabaseConnectionFactory.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar a conta", e);
        }
    }

    @Override
    public void atualizar(Conta conta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public Optional<Conta> buscarPorId(UUID id) {
        String sql = "SELECT * FROM tb_conta co, tb_cliente cl, tb_conta_acesso ca WHERE cl.id = co.cliente_id AND co.id = ca.conta_id AND co.id = '"
                + id.toString() + "'";

        try (Connection conn = DatabaseConnectionFactory.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                Cliente cliente = new Cliente(rs.getString("nome"));
                ContaAcesso contaAcesso = new ContaAcesso(rs.getString("senha"));
                Conta conta = new ContaCorrente(
                        cliente,
                        contaAcesso,
                        new Dinheiro(rs.getBigDecimal("saldo")));
                return Optional.of(conta);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro na busca da conta", e);
        }
        return Optional.empty();
    }

    @Override
    public void remover(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
    }

    @Override
    public List<Conta> buscarTodas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodas'");
    }

}
