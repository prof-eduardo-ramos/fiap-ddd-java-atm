package br.com.fiap.bank.atm.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_contas_acesso")
@Builder
public class ContaAcessoEntity extends BaseEntity {

    // Deixei como constante para ficar fácil de mudar no futuro se precisar.
    public static final Integer MAXIMO_TENTATIVAS = 3;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private Integer tentativas;

    @Column(nullable = false)
    private Boolean bloqueado;

}
