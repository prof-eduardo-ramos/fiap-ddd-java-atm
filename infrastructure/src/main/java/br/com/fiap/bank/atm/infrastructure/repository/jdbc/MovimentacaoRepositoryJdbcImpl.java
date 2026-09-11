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

<<<<<<< HEAD
import br.com.fiap.bank.atm.domain.Movimentacao;
import br.com.fiap.bank.atm.domain.interfaces.ATMRepository;

/**
 * MovimentacaoRepositoryJdbcImpl
 */
public class MovimentacaoRepositoryJdbcImpl implements ATMRepository<Movimentacao> {

    @Override
    public void adicionar(Movimentacao entidade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adicionar'");
=======
import br.com.fiap.bank.atm.domain.Dinheiro;
import br.com.fiap.bank.atm.domain.Movimentacao;
import br.com.fiap.bank.atm.domain.TipoMovimentacao;
import br.com.fiap.bank.atm.domain.interfaces.MovimentacaoRepository;
import br.com.fiap.bank.atm.infrastructure.database.DatabaseConnectionFactory;

public class MovimentacaoRepositoryJdbcImpl implements MovimentacaoRepository {

    @Override
    public void adicionar(Movimentacao entidade) {
        String sql = "INSERT INTO tb_movimentacao (id, conta_id, tipo, valor, data_hora) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entidade.getId().toString());
            stmt.setString(2, entidade.getConta().getId().toString());
            stmt.setString(3, entidade.getTipo().name());
            stmt.setBigDecimal(4, entidade.getValor().getValor());
            stmt.setTimestamp(5, java.sql.Timestamp.valueOf(entidade.getDataHora()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao adicionar a movimentação", e);
        }
>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
    }

    @Override
    public void atualizar(Movimentacao entidade) {
<<<<<<< HEAD
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
=======
        String sql = "UPDATE tb_movimentacao SET tipo = ?, valor = ?, data_hora = ? WHERE id = ?";

        try (Connection conn = DatabaseConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entidade.getTipo().name());
            stmt.setBigDecimal(2, entidade.getValor().getValor());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(entidade.getDataHora()));
            stmt.setString(4, entidade.getId().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar a movimentação", e);
        }
>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
    }

    @Override
    public Optional<Movimentacao> buscarPorId(UUID id) {
<<<<<<< HEAD
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
=======
        String sql = "SELECT * FROM tb_movimentacao WHERE id = ?";

        try (Connection conn = DatabaseConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id.toString());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Movimentacao(
                        rs.getTimestamp("data_hora").toLocalDateTime(),
                        new Dinheiro(rs.getBigDecimal("valor")),
                        TipoMovimentacao.valueOf(rs.getString("tipo"))));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar a movimentação", e);
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
        String sql = "DELETE FROM tb_movimentacao WHERE id = ?";

        try (Connection conn = DatabaseConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao remover a movimentação", e);
        }
>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
    }

    @Override
    public List<Movimentacao> buscarTodas() {
<<<<<<< HEAD
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodas'");
=======
        String sql = "SELECT * FROM tb_movimentacao";

        try (Connection conn = DatabaseConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            List<Movimentacao> movimentacoes = new ArrayList<>();
            while (rs.next()) {
                movimentacoes.add(new Movimentacao(
                        rs.getTimestamp("data_hora").toLocalDateTime(),
                        new Dinheiro(rs.getBigDecimal("valor")),
                        TipoMovimentacao.valueOf(rs.getString("tipo"))));
            }
            return movimentacoes;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar as movimentações", e);
        }
    }

    @Override
    public List<Movimentacao> buscarPorIdConta(UUID idConta) {
        String sql = "SELECT * FROM tb_movimentacao WHERE conta_id = ?";

        try (Connection conn = DatabaseConnectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, idConta.toString());
            ResultSet rs = stmt.executeQuery();
            List<Movimentacao> movimentacoes = new ArrayList<>();
            while (rs.next()) {
                movimentacoes.add(new Movimentacao(
                        rs.getTimestamp("data_hora").toLocalDateTime(),
                        new Dinheiro(rs.getBigDecimal("valor")),
                        TipoMovimentacao.valueOf(rs.getString("tipo"))));
            }
            return movimentacoes;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar as movimentações", e);
        }
>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
    }

}
