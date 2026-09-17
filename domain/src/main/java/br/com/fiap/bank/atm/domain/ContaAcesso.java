package br.com.fiap.bank.atm.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor 
@Entity 
@Table(name = "tb_contas_acesso")
public class ContaAcesso extends BaseEntity {

    // Deixei como constante para ficar fácil de mudar no futuro se precisar.
    public static final Integer MAXIMO_TENTATIVAS = 3;

    @Column(nullable = false)
    private String senha;
    
    @Column(nullable = false)
    private Boolean bloqueado = Boolean.FALSE;

    private Integer tentativas;

    public ContaAcesso(String senha) {
        this.senha = senha;
        this.tentativas = 0;
        this.bloqueado = Boolean.FALSE;
    }

    // Esse método valida a senha e já controla as tentativas automaticamente.
    // Se errar 3 vezes, bloqueia — parecido com o que acontece no banco real.
    public Boolean validarSenha(String senhaInformada) {
        // Se já está bloqueado, nem deixa tentar de novo.
        if (bloqueado) {
            return Boolean.FALSE;
        }
        if (this.senha.equals(senhaInformada)) {
            // Acertou a senha, reseta o contador de tentativas.
            resetarTentativas();
            return Boolean.TRUE;
        }
        tentativas++;
        if (tentativas >= MAXIMO_TENTATIVAS) {
            bloqueado = Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Boolean isBloqueado() {
        return bloqueado;
    }

    // Esse método pode ser usado por um administrador para desbloquear a conta.
    public void resetarTentativas() {
        this.tentativas = 0;
        this.bloqueado = Boolean.FALSE;
    }

}
