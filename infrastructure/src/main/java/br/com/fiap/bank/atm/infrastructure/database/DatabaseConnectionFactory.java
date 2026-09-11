package br.com.fiap.bank.atm.infrastructure.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionFactory {
<<<<<<< HEAD

    private static final String URL_CONEXAO = "jdbc:sqlite:fiapbank.db";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL_CONEXAO);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados", e);
        }
    }
=======
    // A URL aponta para um arquivo local que será criado na raiz do projeto
    private static final String URL = "jdbc:sqlite:fiapbank.db";

    // O método estático getConnection é o ponto de entrada para obter a conexão.
    public static Connection getConnection() {
        try {
            // Carrega o driver JDBC do SQLite e tenta conectar à URL definida.
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            // Em caso de falha, lança uma exceção para notificar o erro de conexão.
            throw new RuntimeException("Erro ao conectar com o banco de dados", e);
        }
    }

>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
}
