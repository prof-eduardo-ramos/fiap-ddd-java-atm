package br.com.fiap.bank.atm.infrastructure;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;

public class Aula17 {
    public static void main(String[] args) {
        Set<BigDecimal> convidados = new TreeSet<>();

        convidados.add(new BigDecimal(23));
        convidados.add(new BigDecimal(100));
        convidados.add(new BigDecimal(4));

        // System.out.println(convidados);

        convidados.stream()
            .filter(valor -> valor.doubleValue() > 10.0)
            .forEach(System.out::println)
        ;

    }

}
