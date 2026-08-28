package br.com.fiap.bank.atm.infrastructure.repository.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.fiap.bank.atm.domain.ContaAcesso;
import br.com.fiap.bank.atm.domain.interfaces.ATMRepository;
import br.com.fiap.bank.atm.infrastructure.database.DatabaseConnectionFactory;

public class ContaAcessoRepositoryJdbcImpl implements ATMRepository<ContaAcesso> {

    @Override
    public void adicionar(ContaAcesso entidade) {
        String sqlInsert = "INSERT INTO tb_conta_acesso (id, senha) VALUES (?, ?)";

        try (Connection conn = DatabaseConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

            stmt.setString(1, entidade.getId().toString());
            stmt.setString(2, entidade.getSenha());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar movimentação", e);
        }

    }

    @Override
    public void atualizar(ContaAcesso entidade) {
        String sqlUpdate = "UPDATE tb_conta_acesso SET senha = ? WHERE id = ?";

        try (Connection conn = DatabaseConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlUpdate)) {

            stmt.setString(1, entidade.getSenha());
            stmt.setString(2, entidade.getId().toString());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar senha", e);
        }

    }

    @Override
    public Optional<ContaAcesso> buscarPorId(UUID id) {
        String sqlSelect = "SELECT * FROM tb_conta_acesso WHERE id = ?";

        try (Connection conn = DatabaseConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlSelect)) {
            stmt.setString(1, id.toString());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ContaAcesso contaAcesso = new ContaAcesso(rs.getString("senha"));
                return Optional.of(contaAcesso);
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar senha", e);
        }
    }

    @Override
    public void remover(UUID id) {
        String sqlDelete = "DELETE FROM tb_conta_acesso WHERE id = ?";

        try (Connection conn = DatabaseConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlDelete)) {
            stmt.setString(1, id.toString());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover senha", e);
        }
    }

    @Override
    public List<ContaAcesso> buscarTodas() {
        String sqlSelect = "SELECT * FROM tb_conta_acesso";

        try (Connection conn = DatabaseConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlSelect)) {
            ResultSet rs = stmt.executeQuery();

            List<ContaAcesso> contasAcesso = new ArrayList<>();
            while (rs.next()) {
                ContaAcesso contaAcesso = new ContaAcesso(rs.getString("senha"));
                contasAcesso.add(contaAcesso);
            }

            return contasAcesso;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter as movimentações", e);
        }
    }
}
