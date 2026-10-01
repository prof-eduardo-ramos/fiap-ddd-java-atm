package br.com.fiap.bank.atm.infrastructure.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_clientes")
public class ClienteEntity extends BaseEntity {

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false)
    private String cpf;

    @Builder
    private ClienteEntity(UUID id, LocalDate dataCriacao, String nomeCompleto, String cpf) {
        super(id, dataCriacao);
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
    }

}
