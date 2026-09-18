package br.com.fiap.bank.atm.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_movimentacoes")
public class Movimentacao extends BaseEntity {

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimentacao tipo;

    @ManyToOne
    private Conta conta;

    @Embedded
    private Dinheiro valor;

    public Movimentacao(Conta conta, LocalDateTime dataHora, Dinheiro valor, TipoMovimentacao tipo) {
        super();
        this.conta = conta;
        this.dataHora = dataHora;
        this.valor = valor;
        this.tipo = tipo;
    }

    public Movimentacao(LocalDateTime dataHora, Dinheiro valor, TipoMovimentacao tipo) {
        this(null, dataHora, valor, tipo);
    }

}
