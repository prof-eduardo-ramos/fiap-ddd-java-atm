package br.com.fiap.bank.atm.infrastructure.repository;

<<<<<<< HEAD
import java.util.List;
import java.util.Optional;
=======
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
import java.util.UUID;

import br.com.fiap.bank.atm.domain.Cliente;
import br.com.fiap.bank.atm.domain.interfaces.ATMRepository;

public class ClienteRepository implements ATMRepository<Cliente> {
<<<<<<< HEAD

    @Override
    public void adicionar(Cliente entidade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adicionar'");
=======
    private Set<Cliente> clientes = new HashSet<>();

    @Override
    public void adicionar(Cliente entidade) {
        clientes.add(entidade);
>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
    }

    @Override
    public Optional<Cliente> buscarPorId(UUID id) {
<<<<<<< HEAD
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
=======
        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
    }

    @Override
    public void remover(UUID id) {
<<<<<<< HEAD
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
=======
        clientes.removeIf(cliente -> cliente.getId().equals(id));
>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
    }

    @Override
    public List<Cliente> buscarTodas() {
<<<<<<< HEAD
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodas'");
=======
        return List.copyOf(clientes);
>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
    }

    @Override
    public void atualizar(Cliente entidade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

}
