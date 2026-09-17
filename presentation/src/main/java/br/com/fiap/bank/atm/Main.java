package br.com.fiap.bank.atm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.fiap.bank.atm.infrastructure.database.DatabaseSetup;

@SpringBootApplication 
public class Main {
    public static void main(String[] args) {
        // DatabaseSetup.criarTabelas();
        SpringApplication.run(Main.class, args);
        System.out.println("🚀 Servidor FIAP Bank ATM rodando na porta 8080!");
    }
}
