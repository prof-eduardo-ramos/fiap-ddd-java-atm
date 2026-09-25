package br.com.fiap.bank.atm.domain;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseEntity {

    protected final UUID id;
    protected final LocalDate dataCriacao;

}
