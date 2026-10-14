package br.com.fiap.bank.atm.domain;

import java.time.LocalDate;
import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
public abstract class BaseEntity {

    protected final UUID id;
    protected final LocalDate dataCriacao;

    protected BaseEntity(UUID id, LocalDate dataCriacao) {
        this.id = id;
        this.dataCriacao = dataCriacao;
    }

}
