package br.com.fiap.bank.atm.domain;

import java.time.LocalDate;
import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
public abstract class BaseEntity {

    private final UUID id;
    private LocalDate dataCriacao;

    public BaseEntity(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }
        this.id = id;
        this.dataCriacao = LocalDate.now();
    }

}
