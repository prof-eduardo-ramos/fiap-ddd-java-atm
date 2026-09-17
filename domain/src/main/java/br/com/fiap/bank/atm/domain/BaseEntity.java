package br.com.fiap.bank.atm.domain;

import java.time.LocalDate;
import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
public abstract class BaseEntity {

    private UUID id;
    private LocalDate dataCriacao;

    public BaseEntity() {
        // UUID gera um identificador único automático, assim não preciso
        // me preocupar em controlar IDs manualmente.
        this.id = UUID.randomUUID();
        this.dataCriacao = LocalDate.now();
    }

}
