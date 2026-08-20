package br.com.fiap.bank.atm.infrastructure;

import br.com.fiap.bank.atm.domain.Conta;
import br.com.fiap.bank.atm.domain.interfaces.ATMRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ContaRepository implements ATMRepository<Conta> {

    private Map<UUID, Conta> contas = new HashMap<>();

    @Deprecated
    public void salvar(Conta conta) {
        contas.put(conta.getId(), conta);
    }

    public void adicionar(Conta entidade) {
        contas.put(entidade.getId(), entidade);
    }

    public Conta buscarPorId(UUID id) {
        return contas.get(id);
    }

    public Boolean existe(UUID id) {
        return contas.containsKey(id);
    }

    @Override
    public void remover(UUID id) {
        contas.remove(id);
    }

    @Override
    public List<Conta> buscarTodas() {
        return List.copyOf(contas.values());
    }
}
