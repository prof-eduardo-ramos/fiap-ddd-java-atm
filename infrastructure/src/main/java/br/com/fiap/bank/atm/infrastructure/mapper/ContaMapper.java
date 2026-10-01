package br.com.fiap.bank.atm.infrastructure.mapper;

import java.math.BigDecimal;

import org.mapstruct.Mapper;

import br.com.fiap.bank.atm.domain.Cliente;
import br.com.fiap.bank.atm.domain.Conta;
import br.com.fiap.bank.atm.domain.ContaAcesso;
import br.com.fiap.bank.atm.domain.Dinheiro;
import br.com.fiap.bank.atm.domain.Movimentacao;
import br.com.fiap.bank.atm.infrastructure.entity.ClienteEntity;
import br.com.fiap.bank.atm.infrastructure.entity.ContaAcessoEntity;
import br.com.fiap.bank.atm.infrastructure.entity.ContaEntity;
import br.com.fiap.bank.atm.infrastructure.entity.MovimentacaoEntity;

@Mapper(componentModel = "spring")
public interface ContaMapper {
    
    ContaEntity toEntity(Conta dominio);

    Conta toDomain(ContaEntity entidade);

    ContaAcesso toDomain(ContaAcessoEntity contaAcessoEntity);

    Cliente toDomain(ClienteEntity clienteEntity);

    ClienteEntity toEntity(Cliente cliente);

    ContaAcessoEntity toEntity(ContaAcesso contaAcesso);

    MovimentacaoEntity toEntity(Movimentacao movimentacao);

    Movimentacao toDomain(MovimentacaoEntity movimentacaoEntity);

    default Dinheiro map(BigDecimal valor) {
        if (valor == null) {
            return new Dinheiro(BigDecimal.ZERO);
        }
        return new Dinheiro(valor);
    }

    default BigDecimal map(Dinheiro dinheiro) {
        if (dinheiro == null) {
            return BigDecimal.ZERO;
        }
        return dinheiro.valor();
    }

}
