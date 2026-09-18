package br.com.fiap.bank.atm.infrastructure.repository.jpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.bank.atm.domain.Conta;
import br.com.fiap.bank.atm.domain.interfaces.ContaRepository;

@Repository
public interface ContaJpaRepository extends ContaRepository, JpaRepository<Conta, UUID> {

    @Override
    default Optional<Conta> buscarPorId(UUID id) {
        return findById(id);
    }

    @Override
    default void adicionar(Conta conta) {
        saveAndFlush(conta);
    }

    @Override
    default void atualizar(Conta conta) {
        save(conta);
    }

    @Override
    default List<Conta> buscarTodas() {
        return findAll();
    }

    @Override
    default void remover(UUID id) {
        deleteById(id);
    }

}
