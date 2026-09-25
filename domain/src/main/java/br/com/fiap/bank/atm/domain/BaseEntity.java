package br.com.fiap.bank.atm.domain;

import java.time.LocalDate;
import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
public abstract class BaseEntity {

    protected UUID id;

    protected LocalDate dataCriacao;

    public BaseEntity() {
        this.dataCriacao = LocalDate.now();
    }

}
