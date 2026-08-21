package br.com.fiap.bank.atm.infrastructure.database;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {
    public static void criarTabelas() {
        String sqlCliente = """
                CREATE TABLE IF NOT EXISTS tb_cliente (
                    id VARCHAR(36) PRIMARY KEY,
                    nome VARCHAR(100) NOT NULL,
                    cpf VARCHAR(14) NOT NULL UNIQUE
                );""";

        String sqlConta = """
                CREATE TABLE IF NOT EXISTS tb_conta (
                    id VARCHAR(36) PRIMARY KEY,
                    cliente_id VARCHAR(36) NOT NULL,
                    agencia VARCHAR(10) NOT NULL,
                    numero VARCHAR(20) NOT NULL UNIQUE,
                    saldo NUMERIC(15, 2) NOT NULL,
                    status VARCHAR(20) NOT NULL,
                    FOREIGN KEY (cliente_id) REFERENCES tb_cliente(id)
                );""";

        String sqlMovimentacao = """
                CREATE TABLE IF NOT EXISTS tb_movimentacao (
                    id VARCHAR(36) PRIMARY KEY,
                    conta_id VARCHAR(36) NOT NULL,
                    tipo VARCHAR(20) NOT NULL,
                    valor NUMERIC(15, 2) NOT NULL,
                    data_hora TIMESTAMP NOT NULL,
                    FOREIGN KEY (conta_id) REFERENCES tb_conta(id)
                );""";

        String sqlAcesso = """
                CREATE TABLE IF NOT EXISTS tb_conta_acesso (
                    id VARCHAR(36) PRIMARY KEY,
                    conta_id VARCHAR(36) NOT NULL,
                    senha VARCHAR(255) NOT NULL,
                    FOREIGN KEY (conta_id) REFERENCES tb_conta(id)
                );""";

        try (Connection conn = DatabaseConnectionFactory.getConnection();
                Statement stmt = conn.createStatement()) {

            stmt.execute(sqlCliente);
            stmt.execute(sqlConta);
            stmt.execute(sqlMovimentacao);
            stmt.execute(sqlAcesso);

            System.out.println("Tabelas criadas com sucesso no SQLite!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
