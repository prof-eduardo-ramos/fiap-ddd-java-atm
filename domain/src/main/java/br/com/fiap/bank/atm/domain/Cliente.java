package br.com.fiap.bank.atm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Cliente extends BaseEntity {

    private String nomeCompleto;
    private String cpf;

    public String obterPrimeiroNome() {
        return nomeCompleto.split(" ")[0];
    }

}
