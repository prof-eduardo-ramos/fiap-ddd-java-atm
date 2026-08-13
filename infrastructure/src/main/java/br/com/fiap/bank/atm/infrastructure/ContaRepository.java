package br.com.fiap.bank.atm.infrastructure;

import br.com.fiap.bank.atm.domain.Conta;
import br.com.fiap.bank.atm.domain.interfaces.ATMRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class ContaRepository implements ATMRepository<Conta> {

    private Map<String, Conta> contas = new HashMap<>();

    @Deprecated
    public void salvar(Conta conta) {
        contas.put(conta.getId().toString(), conta);
    }

    public void adicionar(Conta entidade) {
        String chave = gerarChave(entidade.getAgencia(), entidade.getNumero());
        contas.put(chave, entidade);
    }

    public Conta buscarPorId(UUID id) {
        return contas.get(id);
    }

    public Boolean existe(UUID id) {
        return contas.containsKey(id);
    }

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
    }

    @Override
    public List<Conta> buscarTodas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodas'");
    }
}
