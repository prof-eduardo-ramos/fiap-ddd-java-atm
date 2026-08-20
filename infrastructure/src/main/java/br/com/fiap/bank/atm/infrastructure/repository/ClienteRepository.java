package br.com.fiap.bank.atm.infrastructure.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import br.com.fiap.bank.atm.domain.Cliente;
import br.com.fiap.bank.atm.domain.interfaces.ATMRepository;

public class ClienteRepository implements ATMRepository<Cliente> {
    private Set<Cliente> clientes = new HashSet<>();

    @Override
    public void adicionar(Cliente entidade) {
        clientes.add(entidade);
    }

    @Override
    public Optional<Cliente> buscarPorId(UUID id) {
        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    @Override
    public void remover(UUID id) {
        clientes.removeIf(cliente -> cliente.getId().equals(id));
    }

    @Override
    public List<Cliente> buscarTodas() {
        return List.copyOf(clientes);
    }

    @Override
    public void atualizar(Cliente entidade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

}
