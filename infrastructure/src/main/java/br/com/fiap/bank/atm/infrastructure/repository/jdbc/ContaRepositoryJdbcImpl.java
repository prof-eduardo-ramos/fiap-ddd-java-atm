package br.com.fiap.bank.atm.infrastructure.repository.jdbc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.fiap.bank.atm.domain.Conta;
import br.com.fiap.bank.atm.domain.interfaces.ATMRepository;

public class ContaRepositoryJdbcImpl implements ATMRepository<Conta> {

    @Override
    public void adicionar(Conta entidade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adicionar'");
    }

    @Override
    public void atualizar(Conta entidade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public Optional<Conta> buscarPorId(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }

    @Override
    public void remover(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
    }

    @Override
    public List<Conta> buscarTodas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodas'");
    }

}
