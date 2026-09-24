package br.com.fiap.bank.atm.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_clientes")
public class ClienteEntity extends BaseEntity {
    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false)
    private String cpf;
}
