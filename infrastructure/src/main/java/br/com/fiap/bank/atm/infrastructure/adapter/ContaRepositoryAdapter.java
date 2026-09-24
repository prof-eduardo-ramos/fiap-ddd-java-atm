package br.com.fiap.bank.atm.infrastructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import br.com.fiap.bank.atm.domain.Conta;
import br.com.fiap.bank.atm.domain.interfaces.ContaRepository;
import br.com.fiap.bank.atm.infrastructure.mapper.ContaMapper;
import br.com.fiap.bank.atm.infrastructure.repository.jpa.ContaJpaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
@Primary
public class ContaRepositoryAdapter implements ContaRepository {

    private final ContaJpaRepository repository;
    private final ContaMapper mapper;

    @Override
    public void adicionar(Conta entidade) {
        repository.save(mapper.toEntity(entidade));
    }

    @Override
    public void atualizar(Conta entidade) {
        repository.save(mapper.toEntity(entidade));
    }

    @Override
    public Optional<Conta> buscarPorId(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public void remover(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<Conta> buscarTodas() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

}
