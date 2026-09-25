package br.com.fiap.bank.atm.domain;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Cliente extends BaseEntity {

    private String nomeCompleto;
    private String cpf;

    public Cliente(String nomeCompleto, String cpf) {
        this(null, LocalDate.now(), nomeCompleto, cpf);
    }

    @Builder
    private Cliente(UUID id, LocalDate dataCriacao, String nomeCompleto, String cpf) {
        super(id, dataCriacao);
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
    }

    // Usei split(" ") para pegar só o primeiro nome ao invés do nome completo,
    // fica mais amigável na hora de cumprimentar o usuário no terminal.
    public String obterPrimeiroNome() {
        return nomeCompleto.split(" ")[0];
    }

}
