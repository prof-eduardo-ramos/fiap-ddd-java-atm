package br.com.fiap.bank.atm.infrastructure.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
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
        String sql = "INSERT INTO tb_conta (id, cliente_id, agencia, numero, saldo, status) VALUES (?,?,?,?,?,?)";

        try (Connection conn = DatabaseConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, conta.getId().toString());
            stmt.setString(2, conta.getCliente().getId().toString());
            stmt.setString(3, conta.getAgencia());
            stmt.setString(4, conta.getNumero());
            stmt.setBigDecimal(5, conta.getSaldo().getValor());
            stmt.setString(6, conta.getStatus().name());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar a conta", e);
        }
    }

    @Override
    public void atualizar(Conta conta) {
        String sql = "UPDATE tb_conta SET saldo = ?, status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBigDecimal(1, conta.getSaldo().getValor());
            stmt.setString(2, conta.getStatus().name());
            stmt.setString(3, conta.getId().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar a conta", e);
        }
    }

    @Override
    public Optional<Conta> buscarPorId(UUID id) {
        String sql = "SELECT * FROM tb_conta co, tb_cliente cl, tb_conta_acesso ca WHERE cl.id = co.cliente_id AND co.id = ca.conta_id AND co.id = ?";

        try (Connection conn = DatabaseConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id.toString());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
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
        String sql = "DELETE FROM tb_conta WHERE id = ?";

        try (Connection conn = DatabaseConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover a conta", e);
        }
    }

    @Override
    public List<Conta> buscarTodas() {
        String sql = "SELECT * FROM tb_conta co, tb_cliente cl, tb_conta_acesso ca WHERE cl.id = co.cliente_id AND co.id = ca.conta_id";

        try (Connection conn = DatabaseConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            List<Conta> contas = new ArrayList<>();

            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getString("nome"));
                ContaAcesso contaAcesso = new ContaAcesso(rs.getString("senha"));
                contas.add(new ContaCorrente(
                        cliente,
                        contaAcesso,
                        new Dinheiro(rs.getBigDecimal("saldo"))));
                return contas;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro na busca da conta", e);
        }
        return Collections.emptyList();
    }

}
