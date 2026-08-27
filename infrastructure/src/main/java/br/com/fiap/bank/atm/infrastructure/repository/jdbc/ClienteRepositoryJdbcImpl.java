package br.com.fiap.bank.atm.infrastructure.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.fiap.bank.atm.domain.Cliente;
import br.com.fiap.bank.atm.domain.interfaces.ATMRepository;
import br.com.fiap.bank.atm.infrastructure.database.DatabaseConnectionFactory;

public class ClienteRepositoryJdbcImpl implements ATMRepository<Cliente> {

    @Override
    public void adicionar(Cliente cliente) {
        String sqlInsert = "INSERT INTO tb_cliente (id, nome) VALUES (?, ?)";

        try(Connection conn = DatabaseConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

            stmt.setString(1, cliente.getId().toString());
            stmt.setString(2, cliente.getNomeCompleto());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar movimentação", e);
        }

    }

    @Override
    public void atualizar(Cliente cliente) {
        String sqlUpdate = "UPDATE tb_cliente SET nome = ? WHERE id = ?";

        try(Connection conn = DatabaseConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sqlUpdate)) {

            stmt.setString(1, cliente.getNomeCompleto());
            stmt.setString(2, cliente.getId().toString());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar movimentação", e);
        }
    }

    @Override
    public Optional<Cliente> buscarPorId(UUID id) {
        String sqlSelect = "SELECT * FROM tb_cliente WHERE id = ?";

        try(Connection conn = DatabaseConnectionFactory.getConnection(); PreparedStatement stmt = conn.prepareStatement(sqlSelect)) {
            stmt.setString(1, id.toString());
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                Cliente cliente = new Cliente(rs.getString("nome"));
                return Optional.of(cliente);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter as movimentações", e);
        }
        return Optional.empty();
    }

    @Override
    public void remover(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
    }

    @Override
    public List<Cliente> buscarTodas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodas'");
    }

}
