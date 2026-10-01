package br.com.fiap.bank.atm.infrastructure.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_contas")
public class ContaEntity extends BaseEntity {

    @Column(nullable = false, length = 10)
    protected String numero;

    @Column(nullable = false, length = 4)
    protected String agencia;

    @Column(nullable = false)
    protected Double taxa;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected StatusContaEnum status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected TipoContaEnum tipo;

    @Column(nullable = false)
    protected LocalDate dataAbertura;

    @Column(nullable = false, precision = 10, scale = 2)
    protected BigDecimal saldo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    protected ClienteEntity cliente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_conta_acesso", nullable = false)
    protected ContaAcessoEntity contaAcesso;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    protected List<MovimentacaoEntity> movimentacoes;

    @Builder
    private ContaEntity(UUID id, LocalDate dataCriacao, String numero, String agencia, Double taxa,
            StatusContaEnum status,
            TipoContaEnum tipo, LocalDate dataAbertura, BigDecimal saldo, ClienteEntity cliente,
            ContaAcessoEntity contaAcesso, List<MovimentacaoEntity> movimentacoes) {
        super(id, dataCriacao);
        this.numero = numero;
        this.agencia = agencia;
        this.taxa = taxa;
        this.status = status;
        this.tipo = tipo;
        this.dataAbertura = dataAbertura;
        this.saldo = saldo;
        this.cliente = cliente;
        this.contaAcesso = contaAcesso;
        this.movimentacoes = (movimentacoes == null) ? new ArrayList<>() : movimentacoes;
    }

}
