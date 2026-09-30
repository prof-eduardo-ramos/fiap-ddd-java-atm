package br.com.fiap.bank.atm.infrastructure.entity;

public enum TipoContaEnum {
    CONTA_CORRENTE,
    CONTA_POUPANCA;

    public static TipoContaEnum from(String name) {
        return TipoContaEnum.valueOf(name);
    }
}
