package br.com.fiap.bank.atm.application.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<Cpf, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        // Remove pontuação de formatação: 000.000.000-00 -> 00000000000
        String cleanCpf = value.replaceAll("\\D", "");

        if (cleanCpf.length() != 11) {
            return false;
        }

        // Rejeita sequências conhecidas de dígitos iguais
        if (cleanCpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            // Cálculo do 1º Dígito Verificador
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += (cleanCpf.charAt(i) - '0') * (10 - i);
            }
            int primeiroDigito = 11 - (soma % 11);
            if (primeiroDigito >= 10) {
                primeiroDigito = 0;
            }

            if (primeiroDigito != (cleanCpf.charAt(9) - '0')) {
                return false;
            }

            // Cálculo do 2º Dígito Verificador
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += (cleanCpf.charAt(i) - '0') * (11 - i);
            }
            int segundoDigito = 11 - (soma % 11);
            if (segundoDigito >= 10) {
                segundoDigito = 0;
            }

            return segundoDigito == (cleanCpf.charAt(10) - '0');

        } catch (Exception e) {
            return false;
        }
    }

}
