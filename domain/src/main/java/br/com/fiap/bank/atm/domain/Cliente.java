package br.com.fiap.bank.atm.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_clientes")
public class Cliente extends BaseEntity {

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false)
    private String cpf;

    // Usei split(" ") para pegar só o primeiro nome ao invés do nome completo,
    // fica mais amigável na hora de cumprimentar o usuário no terminal.
    public String obterPrimeiroNome() {
        return nomeCompleto.split(" ")[0];
    }

}
