package br.com.fiap.bank.atm.infrastructure.entity;

public enum StatusContaEnum {
    ATIVA, BLOQUEADA, ENCERRADA;

    public static StatusContaEnum from(String name) {
        return StatusContaEnum.valueOf(name);
    }
}
