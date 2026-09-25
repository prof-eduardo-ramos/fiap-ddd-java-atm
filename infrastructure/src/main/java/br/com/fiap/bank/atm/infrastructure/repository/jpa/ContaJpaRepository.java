package br.com.fiap.bank.atm.infrastructure.repository.jpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.bank.atm.infrastructure.entity.ContaEntity;

@Repository
public interface ContaJpaRepository extends JpaRepository<ContaEntity, UUID> {
    // Derived Query Method
    Optional<ContaEntity> findByNumero(String numero);

    Optional<ContaEntity> findByAgenciaAndNumero(String agencia, String numero);

    // Consulta customizada orientada a objetos (JPQL)
    @Query("SELECT c FROM ContaEntity c LEFT JOIN FETCH c.movimentacoes WHERE c.numero = :numero")
    Optional<ContaEntity> findByNumeroComMovimentacoes(@Param("numero") String numero);
}
