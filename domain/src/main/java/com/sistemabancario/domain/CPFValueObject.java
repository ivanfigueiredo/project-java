package com.sistemabancario.domain;

import com.sistemabancario.domain.exceptions.DomainException;
import com.sun.jdi.Type;

import java.util.*;

public class CPFValueObject {
    private final String cpf;

    private CPFValueObject(final String cpf) {
        this.cpf = cpf;
    }

    public String getValue() {
        return this.cpf;
    }

    private static boolean iteratorDigits(final int digit, final List<String> digitsCpf) {
        int soma = 0;
        int multiplier = 2;
        for (int i = digit; i >= 0; i--) {
            soma += Integer.parseInt(digitsCpf.get(i)) * multiplier;
            multiplier++;
        }
        return ((soma * 10) % 11) == Integer.parseInt(digitsCpf.get(digit + 1));
    }

    private static void isValidCPF(final String cpf) {
        boolean isValid = false;
        if (cpf.isBlank()) throw new DomainException("Invalid CPF");
        List<String> digits = Arrays.stream(cpf.replaceAll("/[^0-9]", "").split("")).toList();
        if (!isDigitsEqual(digits)) throw new DomainException("Invalid CPF");
        for (int i = 0; i < 2; i++) {
            isValid = iteratorDigits(8 + i, digits);
        }
        if (!isValid) throw new DomainException("Invalid CPF");
    }

    private static boolean isDigitsEqual(final List<String> digits) {
        Set<String> set = new HashSet<String>(digits);
        return (set.size() > 1);
    }

    public static CPFValueObject create(final String cpf) {
        isValidCPF(cpf);
        return new CPFValueObject(cpf);
    }
}
