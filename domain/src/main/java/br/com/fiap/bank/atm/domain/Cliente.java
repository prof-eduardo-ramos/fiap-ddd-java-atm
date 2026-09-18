package br.com.fiap.bank.atm.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_clientes")
public class Cliente extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String nomeCompleto;

    @Column(nullable = false, length = 14)
    private String cpf;

    public String obterPrimeiroNome() {
        return nomeCompleto.split(" ")[0];
    }

}
