package br.com.fiap.bank.atm.infrastructure.entity;

public enum TipoMovimentacaoEnum {
    DEPOSITO, SAQUE, TAXA, RENDIMENTO;

    public static TipoMovimentacaoEnum from(String name) {
        return TipoMovimentacaoEnum.valueOf(name);
    }
}
