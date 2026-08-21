package br.com.fiap.bank.atm.domain.interfaces;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.fiap.bank.atm.domain.BaseEntity;

public interface ATMRepository<T extends BaseEntity> {

    void adicionar(T entidade);

    void atualizar(T entidade);

    Optional<T> buscarPorId(UUID id);

    void remover(UUID id);

    List<T> buscarTodas();

}
