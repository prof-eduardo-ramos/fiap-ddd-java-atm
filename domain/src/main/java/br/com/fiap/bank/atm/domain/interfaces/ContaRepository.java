package br.com.fiap.bank.atm.domain.interfaces;

import java.util.Optional;

import br.com.fiap.bank.atm.domain.Conta;

public interface ContaRepository extends ATMRepository<Conta> {
    Optional<Conta> buscarPorNumero(String numero);
}
