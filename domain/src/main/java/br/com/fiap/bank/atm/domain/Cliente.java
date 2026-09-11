package br.com.fiap.bank.atm.domain;

// Representa o cliente do banco. Estende BaseEntity para já ter id e data de criação.
public class Cliente extends BaseEntity {

    private String nomeCompleto;
    private String cpf;

    public Cliente(String nomeCompleto, String cpf) {
        super();
        if (nomeCompleto == null || nomeCompleto.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome completo é obrigatório.");
        }
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF é obrigatório.");
        }
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
    }

    // Usei split(" ") para pegar só o primeiro nome ao invés do nome completo,
    // fica mais amigável na hora de cumprimentar o usuário no terminal.
    public String obterPrimeiroNome() {
        return nomeCompleto.split(" ")[0];
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
