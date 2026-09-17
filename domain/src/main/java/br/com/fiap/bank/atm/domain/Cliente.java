package br.com.fiap.bank.atm.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor 
@Entity
@Table(name = "tb_clientes")
public class Cliente extends BaseEntity {

    @Column(nullable = false, length = 50)
    private String nomeCompleto;

    @Column(nullable = false, length = 11)
    private String cpf;

    public Cliente(String nomeCompleto, String cpf) {
        super();
        if (nomeCompleto == null || nomeCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome completo é obrigatório.");
        }
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF é obrigatório.");
        }
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
    }

    // Usei split(" ") para pegar só o primeiro nome ao invés do nome completo,
    // fica mais amigável na hora de cumprimentar o usuário no terminal.
    public String obterPrimeiroNome() {
        return nomeCompleto.split(" ")[0];
    }

}
