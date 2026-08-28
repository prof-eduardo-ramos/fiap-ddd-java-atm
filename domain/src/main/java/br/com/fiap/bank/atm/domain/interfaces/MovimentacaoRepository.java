package br.com.fiap.bank.atm.domain.interfaces;

import java.util.List;
import java.util.UUID;

import br.com.fiap.bank.atm.domain.Movimentacao;

public interface MovimentacaoRepository extends ATMRepository<Movimentacao> {

    List<Movimentacao> buscarPorIdConta(UUID idConta);

}
