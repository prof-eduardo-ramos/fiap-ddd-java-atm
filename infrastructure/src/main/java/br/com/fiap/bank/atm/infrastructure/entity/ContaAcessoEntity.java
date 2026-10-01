package br.com.fiap.bank.atm.infrastructure.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_contas_acesso")
public class ContaAcessoEntity extends BaseEntity {

    // Deixei como constante para ficar fácil de mudar no futuro se precisar.
    public static final Integer MAXIMO_TENTATIVAS = 3;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private Integer tentativas;

    @Column(nullable = false)
    private Boolean bloqueado;

    @Builder
    private ContaAcessoEntity(UUID id, LocalDate dataCriacao, String senha, Integer tentativas, Boolean bloqueado) {
        super(id, dataCriacao);
        this.senha = senha;
        this.tentativas = tentativas;
        this.bloqueado = bloqueado;
    }

}
