package br.com.fiap.bank.atm.infrastructure.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionFactory {

    private static final String URL_CONEXAO = "jdbc:sqlite:fiapbank.db";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL_CONEXAO);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados", e);
        }
    }
}
