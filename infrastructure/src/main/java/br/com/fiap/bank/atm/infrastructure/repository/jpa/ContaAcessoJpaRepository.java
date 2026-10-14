package br.com.fiap.bank.atm.infrastructure.repository.jpa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.bank.atm.infrastructure.entity.ContaAcessoEntity;

public interface ContaAcessoJpaRepository extends JpaRepository<ContaAcessoEntity, UUID> {

}
