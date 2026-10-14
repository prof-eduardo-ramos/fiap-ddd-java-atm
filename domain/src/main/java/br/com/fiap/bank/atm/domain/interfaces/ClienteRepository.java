package br.com.fiap.bank.atm.domain.interfaces;

import java.util.Optional;

import br.com.fiap.bank.atm.domain.Cliente;

public interface ClienteRepository extends ATMRepository<Cliente> {

    Optional<Cliente> buscarPorCpf(String cpf);

}
