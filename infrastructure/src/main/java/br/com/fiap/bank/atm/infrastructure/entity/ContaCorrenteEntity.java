package br.com.fiap.bank.atm.infrastructure.entity;

import java.math.BigDecimal;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@DiscriminatorValue("CONTA_CORRENTE")
@Entity
public class ContaCorrenteEntity extends ContaEntity {

}
