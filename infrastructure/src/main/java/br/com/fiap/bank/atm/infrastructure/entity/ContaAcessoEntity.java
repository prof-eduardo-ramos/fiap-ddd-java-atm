package br.com.fiap.bank.atm.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_contas_acesso")
public class ContaAcessoEntity extends BaseEntity {
    public static final Integer MAXIMO_TENTATIVAS = 3;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private Integer tentativas;

    @Column(nullable = false)
    private Boolean bloqueado;

    public ContaAcessoEntity(String senha) {
        super();
        this.senha = senha;
        this.tentativas = 0;
        this.bloqueado = Boolean.FALSE;
    }
}
