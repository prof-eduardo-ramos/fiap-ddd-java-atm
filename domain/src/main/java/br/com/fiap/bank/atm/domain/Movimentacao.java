package br.com.fiap.bank.atm.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    
    
    private Dinheiro valor;

    public Movimentacao(LocalDateTime dataHora, Dinheiro valor, TipoMovimentacao tipo) {
        super();
        this.dataHora = dataHora;
        this.valor = valor;
        this.tipo = tipo;
    }
}
