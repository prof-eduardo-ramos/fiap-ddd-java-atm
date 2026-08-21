package br.com.fiap.bank.atm.infrastructure.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionFactory {
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

}
