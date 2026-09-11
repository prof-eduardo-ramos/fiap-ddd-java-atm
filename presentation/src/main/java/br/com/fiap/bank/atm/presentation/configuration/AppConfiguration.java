package br.com.fiap.bank.atm.presentation.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.bank.atm.application.ContaService;
import br.com.fiap.bank.atm.infrastructure.repository.jdbc.ContaRepositoryJdbcImpl;
import br.com.fiap.bank.atm.infrastructure.repository.jdbc.MovimentacaoRepositoryJdbcImpl;

@Configuration
public class AppConfiguration {

    @Bean
    public ContaService contaService() {
        return new ContaService(new ContaRepositoryJdbcImpl(), new MovimentacaoRepositoryJdbcImpl());
    }

}
