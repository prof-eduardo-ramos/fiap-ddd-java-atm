package br.com.fiap.bank.atm.infrastructure.repository.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.bank.atm.infrastructure.entity.ContaEntity;

public interface ContaJpaRepository extends JpaRepository<ContaEntity, UUID> {
    Optional<ContaEntity> findByNumero(String numero);
}
