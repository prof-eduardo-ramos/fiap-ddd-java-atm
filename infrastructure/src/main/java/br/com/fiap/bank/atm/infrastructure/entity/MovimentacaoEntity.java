package br.com.fiap.bank.atm.infrastructure.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_movimentacoes")
public class MovimentacaoEntity extends BaseEntity {

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    private TipoMovimentacaoEnum tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conta_id", nullable = false)
    private ContaEntity conta;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    public MovimentacaoEntity(ContaEntity conta, LocalDateTime dataHora, BigDecimal valor, TipoMovimentacaoEnum tipo) {
        super();
        this.conta = conta;
        this.dataHora = dataHora;
        this.valor = valor;
        this.tipo = tipo;
    }

    public MovimentacaoEntity(LocalDateTime dataHora, BigDecimal valor, TipoMovimentacaoEnum tipo) {
        this(null, dataHora, valor, tipo);
    }

}
