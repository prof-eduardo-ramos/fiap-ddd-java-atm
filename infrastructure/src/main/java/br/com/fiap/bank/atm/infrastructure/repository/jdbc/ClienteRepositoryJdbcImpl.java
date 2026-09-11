package br.com.fiap.bank.atm.infrastructure.repository.jdbc;

<<<<<<< HEAD
=======
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.fiap.bank.atm.domain.Cliente;
<<<<<<< HEAD
import br.com.fiap.bank.atm.domain.interfaces.ATMRepository;

public class ClienteRepositoryJdbcImpl implements ATMRepository<Cliente> {

    @Override
    public void adicionar(Cliente entidade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adicionar'");
    }

    @Override
    public void atualizar(Cliente entidade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
=======
import br.com.fiap.bank.atm.domain.interfaces.ClienteRepository;
import br.com.fiap.bank.atm.infrastructure.database.DatabaseConnectionFactory;

public class ClienteRepositoryJdbcImpl implements ClienteRepository {

    @Override
    public void adicionar(Cliente cliente) {
        String sqlInsert = "INSERT INTO tb_cliente (id, nome) VALUES (?, ?)";

        try (Connection conn = DatabaseConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

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

        try (Connection conn = DatabaseConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlUpdate)) {

            stmt.setString(1, cliente.getNomeCompleto());
            stmt.setString(2, cliente.getId().toString());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar movimentação", e);
        }
>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
    }

    @Override
    public Optional<Cliente> buscarPorId(UUID id) {
<<<<<<< HEAD
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
=======
        String sqlSelect = "SELECT * FROM tb_cliente WHERE id = ?";

        try (Connection conn = DatabaseConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlSelect)) {
            stmt.setString(1, id.toString());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente(rs.getString("nome"), rs.getString("cpf"));
                return Optional.of(cliente);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter as movimentações", e);
        }
        return Optional.empty();
>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
    }

    @Override
    public void remover(UUID id) {
<<<<<<< HEAD
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
=======
        String sqlDelete = "DELETE FROM tb_cliente WHERE id = ?";

        try (Connection conn = DatabaseConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlDelete)) {
            stmt.setString(1, id.toString());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover cliente", e);
        }
>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
    }

    @Override
    public List<Cliente> buscarTodas() {
<<<<<<< HEAD
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodas'");
=======
        String sqlSelect = "SELECT * FROM tb_cliente";

        try (Connection conn = DatabaseConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlSelect)) {
            ResultSet rs = stmt.executeQuery();

            List<Cliente> clientes = new ArrayList<>();
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getString("nome"), rs.getString("cpf"));
                clientes.add(cliente);
            }

            return clientes;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter as movimentações", e);
        }
>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
    }

}
