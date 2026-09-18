package br.com.fiap.bank.atm.infrastructure.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@DiscriminatorValue("CONTA_POUPANCA")
public class ContaPoupancaEntity extends ContaEntity {

}
