package br.com.fiap.bank.atm.infrastructure.repository.jdbc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.fiap.bank.atm.domain.Movimentacao;
import br.com.fiap.bank.atm.domain.interfaces.MovimentacaoRepository;

public class MovimentacaoRepositoryJdbcImpl implements MovimentacaoRepository {

    @Override
    public void adicionar(Movimentacao entidade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'adicionar'");
    }

    @Override
    public void atualizar(Movimentacao entidade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public Optional<Movimentacao> buscarPorId(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorId'");
    }

    @Override
    public void remover(UUID id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remover'");
    }

    @Override
    public List<Movimentacao> buscarTodas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarTodas'");
    }

    @Override
    public List<Movimentacao> buscarPorIdConta(UUID idConta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorIdConta'");
    }

}
