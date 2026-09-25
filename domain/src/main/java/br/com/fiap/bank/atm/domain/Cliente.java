package br.com.fiap.bank.atm.domain;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
// @AllArgsConstructor
public class Cliente extends BaseEntity {

    private String nomeCompleto;
    private String cpf;

    public Cliente(String nomeCompleto, String cpf) {
        this(UUID.randomUUID(), nomeCompleto, cpf);
    }

    @Builder
    private Cliente(UUID id, String nomeCompleto, String cpf) {
        super(id);
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
    }

    // Usei split(" ") para pegar só o primeiro nome ao invés do nome completo,
    // fica mais amigável na hora de cumprimentar o usuário no terminal.
    public String obterPrimeiroNome() {
        return nomeCompleto.split(" ")[0];
    }

}
