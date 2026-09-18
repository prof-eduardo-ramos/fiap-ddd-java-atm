package br.com.fiap.bank.atm.infrastructure.mapper;

import java.math.BigDecimal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.fiap.bank.atm.domain.Cliente;
import br.com.fiap.bank.atm.domain.Conta;
import br.com.fiap.bank.atm.domain.ContaAcesso;
import br.com.fiap.bank.atm.domain.ContaCorrente;
import br.com.fiap.bank.atm.domain.Dinheiro;
import br.com.fiap.bank.atm.domain.Movimentacao;
import br.com.fiap.bank.atm.infrastructure.entity.ClienteEntity;
import br.com.fiap.bank.atm.infrastructure.entity.ContaAcessoEntity;
import br.com.fiap.bank.atm.infrastructure.entity.ContaCorrenteEntity;
import br.com.fiap.bank.atm.infrastructure.entity.ContaEntity;
import br.com.fiap.bank.atm.infrastructure.entity.MovimentacaoEntity;

@Mapper(componentModel = "spring")
public abstract class ContaMapper {

    @Mapping(target = "saldo", source = "saldo.valor")
    public abstract ContaCorrenteEntity toEntity(ContaCorrente conta);

    public Conta toDomain(ContaEntity contaEntity) {
        if (contaEntity instanceof ContaCorrenteEntity) {
            return toDomain((ContaCorrenteEntity) contaEntity);
        }
        return null;
    }

    public ContaEntity toEntity(Conta conta) {
        if (conta instanceof ContaCorrente) {
            return toEntity((ContaCorrente) conta);
        }
        return null;
    }

    public abstract ContaAcesso toDomain(ContaAcessoEntity contaAcessoEntity);

    public abstract Cliente toDomain(ClienteEntity clienteEntity);

    public abstract ClienteEntity toEntity(Cliente cliente);

    public abstract ContaAcessoEntity toEntity(ContaAcesso contaAcesso);

    public abstract MovimentacaoEntity toEntity(Movimentacao movimentacao);

    public abstract Movimentacao toDomain(MovimentacaoEntity movimentacaoEntity);

    public abstract Dinheiro map(BigDecimal valor);

    protected abstract ContaCorrente toDomain(ContaCorrenteEntity contaCorrenteEntity);

}
