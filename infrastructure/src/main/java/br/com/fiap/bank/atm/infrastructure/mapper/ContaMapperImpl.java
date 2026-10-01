package br.com.fiap.bank.atm.infrastructure.mapper;

import org.springframework.stereotype.Component;

import br.com.fiap.bank.atm.domain.Cliente;
import br.com.fiap.bank.atm.domain.Conta;
import br.com.fiap.bank.atm.domain.ContaAcesso;
import br.com.fiap.bank.atm.domain.Movimentacao;
import br.com.fiap.bank.atm.domain.StatusConta;
import br.com.fiap.bank.atm.domain.TipoConta;
import br.com.fiap.bank.atm.domain.TipoMovimentacao;
import br.com.fiap.bank.atm.infrastructure.entity.ClienteEntity;
import br.com.fiap.bank.atm.infrastructure.entity.ContaAcessoEntity;
import br.com.fiap.bank.atm.infrastructure.entity.ContaEntity;
import br.com.fiap.bank.atm.infrastructure.entity.MovimentacaoEntity;
import br.com.fiap.bank.atm.infrastructure.entity.StatusContaEnum;
import br.com.fiap.bank.atm.infrastructure.entity.TipoContaEnum;
import br.com.fiap.bank.atm.infrastructure.entity.TipoMovimentacaoEnum;

@Component
public class ContaMapperImpl implements ContaMapper {

    @Override
    public ContaEntity toEntity(Conta conta) {
        if (conta == null) {
            return null;
        }

        ContaEntity.ContaEntityBuilder builder = ContaEntity.builder();

        builder.id(conta.getId());
        builder.dataCriacao(conta.getDataCriacao());
        builder.agencia(conta.getAgencia());
        builder.cliente(toEntity(conta.getCliente()));
        builder.contaAcesso(toEntity(conta.getContaAcesso()));
        builder.dataAbertura(conta.getDataAbertura());
        builder.numero(conta.getNumero());
        builder.saldo(map(conta.getSaldo()));
        builder.status(StatusContaEnum.from(conta.getStatus().name()));
        builder.taxa(conta.getTaxa());
        builder.tipo(TipoContaEnum.from(conta.getTipo().name()));
        ContaEntity contaEntity = builder.build();
        conta.getMovimentacoes().forEach(m -> contaEntity.getMovimentacoes().add(this.toEntity(m, conta)));

        return contaEntity;
    }

    @Override
    public Conta toDomain(ContaEntity contaEntity) {
        if (contaEntity == null) {
            return null;
        }

        Conta.ContaBuilder builder = Conta.builder();

        builder.id(contaEntity.getId());
        builder.dataCriacao(contaEntity.getDataCriacao());
        builder.numero(contaEntity.getNumero());
        builder.agencia(contaEntity.getAgencia());
        builder.cliente(toDomain(contaEntity.getCliente()));
        builder.contaAcesso(toDomain(contaEntity.getContaAcesso()));
        builder.saldo(map(contaEntity.getSaldo()));
        builder.taxa(contaEntity.getTaxa());
        builder.status(StatusConta.from(contaEntity.getStatus().name()));
        builder.tipo(TipoConta.from(contaEntity.getTipo().name()));
        builder.dataAbertura(contaEntity.getDataAbertura());

        Conta conta = builder.build();
        conta.getMovimentacoes().addAll(contaEntity.getMovimentacoes().stream().map(this::toDomain).toList());

        return conta;
    }

    @Override
    public ContaAcesso toDomain(ContaAcessoEntity contaAcessoEntity) {
        if (contaAcessoEntity == null) {
            return null;
        }

        ContaAcesso.ContaAcessoBuilder contaAcesso = ContaAcesso.builder();

        contaAcesso.id(contaAcessoEntity.getId());
        contaAcesso.dataCriacao(contaAcessoEntity.getDataCriacao());
        contaAcesso.senha(contaAcessoEntity.getSenha());
        contaAcesso.tentativas(contaAcessoEntity.getTentativas());
        contaAcesso.bloqueado(contaAcessoEntity.getBloqueado());

        return contaAcesso.build();
    }

    @Override
    public Cliente toDomain(ClienteEntity clienteEntity) {
        if (clienteEntity == null) {
            return null;
        }

        Cliente.ClienteBuilder cliente = Cliente.builder();

        cliente.id(clienteEntity.getId());
        cliente.dataCriacao(clienteEntity.getDataCriacao());
        cliente.nomeCompleto(clienteEntity.getNomeCompleto());
        cliente.cpf(clienteEntity.getCpf());

        return cliente.build();
    }

    @Override
    public ClienteEntity toEntity(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        ClienteEntity.ClienteEntityBuilder clienteEntity = ClienteEntity.builder();

        clienteEntity.id(cliente.getId());
        clienteEntity.dataCriacao(cliente.getDataCriacao());
        clienteEntity.nomeCompleto(cliente.getNomeCompleto());
        clienteEntity.cpf(cliente.getCpf());

        return clienteEntity.build();
    }

    @Override
    public ContaAcessoEntity toEntity(ContaAcesso contaAcesso) {
        if (contaAcesso == null) {
            return null;
        }

        ContaAcessoEntity.ContaAcessoEntityBuilder contaAcessoEntity = ContaAcessoEntity.builder();

        contaAcessoEntity.id(contaAcesso.getId());
        contaAcessoEntity.dataCriacao(contaAcesso.getDataCriacao());
        contaAcessoEntity.senha(contaAcesso.getSenha());
        contaAcessoEntity.tentativas(contaAcesso.getTentativas());
        contaAcessoEntity.bloqueado(contaAcesso.getBloqueado());

        return contaAcessoEntity.build();
    }

    @Override
    public MovimentacaoEntity toEntity(Movimentacao movimentacao) {
        MovimentacaoEntity.MovimentacaoEntityBuilder movimentacaoEntity = MovimentacaoEntity.builder();

        movimentacaoEntity.id(movimentacao.getId());
        movimentacaoEntity.dataCriacao(movimentacao.getDataCriacao());
        movimentacaoEntity.dataHora(movimentacao.getDataHora());
        movimentacaoEntity.valor(map(movimentacao.getValor()));
        movimentacaoEntity.tipo(TipoMovimentacaoEnum.from(movimentacao.getTipo().name()));

        return movimentacaoEntity.build();
    }

    public MovimentacaoEntity toEntity(Movimentacao movimentacao, Conta conta) {
        MovimentacaoEntity movimentacaoEntity = this.toEntity(movimentacao);
        ContaEntity contaEntity = ContaEntity.builder()
                .id(conta.getId())
                .dataCriacao(conta.getDataCriacao())
                .numero(conta.getNumero())
                .agencia(conta.getAgencia())
                .cliente(this.toEntity(conta.getCliente()))
                .contaAcesso(this.toEntity(conta.getContaAcesso()))
                .saldo(map(conta.getSaldo()))
                .taxa(conta.getTaxa())
                .status(StatusContaEnum.from(conta.getStatus().name()))
                .tipo(TipoContaEnum.from(conta.getTipo().name()))
                .dataAbertura(conta.getDataAbertura())
                .build();
        movimentacaoEntity.setConta(contaEntity);
        return movimentacaoEntity;
    }

    public Movimentacao toDomain(MovimentacaoEntity movimentacaoEntity) {
        Movimentacao.MovimentacaoBuilder movimentacao = Movimentacao.builder();

        movimentacao.id(movimentacaoEntity.getId());
        movimentacao.dataCriacao(movimentacaoEntity.getDataCriacao());
        movimentacao.dataHora(movimentacaoEntity.getDataHora());
        movimentacao.valor(map(movimentacaoEntity.getValor()));
        movimentacao.tipo(TipoMovimentacao.from(movimentacaoEntity.getTipo().name()));

        return movimentacao.build();
    }

}
