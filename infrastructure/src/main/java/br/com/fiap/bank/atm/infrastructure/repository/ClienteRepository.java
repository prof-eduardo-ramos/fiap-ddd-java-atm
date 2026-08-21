package br.com.fiap.bank.atm.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.fiap.bank.atm.domain.Cliente;
import br.com.fiap.bank.atm.domain.interfaces.ATMRepository;

public class ClienteRepository implements ATMRepository<Cliente> {

    @Override
    public void adicionar(Cliente entidade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adicionar'");
    }

    @Override
    public Optional<Cliente> buscarPorId(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }

    @Override
    public void remover(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
    }

    @Override
    public List<Cliente> buscarTodas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodas'");
    }

    @Override
    public void atualizar(Cliente entidade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

}
