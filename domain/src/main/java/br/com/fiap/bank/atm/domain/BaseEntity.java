package br.com.fiap.bank.atm.domain;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    protected UUID id;

    @Column(nullable = false)
    protected LocalDate dataCriacao;

    public BaseEntity() {
        // UUID gera um identificador único automático, assim não preciso
        // me preocupar em controlar IDs manualmente.
        this.id = UUID.randomUUID();
        this.dataCriacao = LocalDate.now();
    }

}
