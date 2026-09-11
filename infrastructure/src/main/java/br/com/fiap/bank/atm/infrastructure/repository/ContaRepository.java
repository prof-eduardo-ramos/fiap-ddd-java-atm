package br.com.fiap.bank.atm.infrastructure.repository;

import br.com.fiap.bank.atm.domain.Conta;
import br.com.fiap.bank.atm.domain.interfaces.ATMRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class ContaRepository implements ATMRepository<Conta> {

<<<<<<< HEAD
    private Map<String, Conta> contas = new HashMap<>();

    @Deprecated
    public void salvar(Conta conta) {
        contas.put(conta.getId().toString(), conta);
    }

    public void adicionar(Conta entidade) {
        String chave = gerarChave(entidade.getAgencia(), entidade.getNumero());
        contas.put(chave, entidade);
=======
    private Map<UUID, Conta> contas = new HashMap<>();

    @Deprecated
    public void salvar(Conta conta) {
        contas.put(conta.getId(), conta);
    }

    public void adicionar(Conta entidade) {
        contas.put(entidade.getId(), entidade);
>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
    }

    public Optional<Conta> buscarPorId(UUID id) {
        return Optional.ofNullable(contas.get(id));
    }

    public Boolean existe(UUID id) {
        return contas.containsKey(id);
    }

<<<<<<< HEAD
    // Busca O(1) de altíssima performance (mas ainda retornando null se não achar)
    public Conta validarContaNoAtm(String agencia, String numero) {
        return contas.get(gerarChave(agencia, numero));
    }

    // Helper para padronizar a chave composta
    private String gerarChave(String agencia, String numero) {
        return agencia + "-" + numero;
    }

    @Override
    public void remover(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
=======
    @Override
    public void remover(UUID id) {
        contas.remove(id);
>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
    }

    @Override
    public List<Conta> buscarTodas() {
<<<<<<< HEAD
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodas'");
=======
        return List.copyOf(contas.values());
>>>>>>> 83c1325e2aa2142231e68628e45dd5bf286b76ab
    }

    @Override
    public void atualizar(Conta entidade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }
}
