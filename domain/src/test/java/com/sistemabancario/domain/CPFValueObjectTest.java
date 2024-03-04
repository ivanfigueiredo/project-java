package com.sistemabancario.domain;

import com.sistemabancario.domain.exceptions.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CPFValueObjectTest {


    @Test
    public void shouldCreateCPFWithSuccess() {
        CPFValueObject cpf = CPFValueObject.create("59138058014");

        Assertions.assertNotNull(cpf);
        Assertions.assertEquals(cpf.getValue(), "59138058014");
    }

    @Test
    public void shouldNotCreateACPFIfDigitsEquals() {
        final String expectedMessageError = "Invalid CPF";
        final var exception = Assertions.assertThrows(DomainException.class, () -> CPFValueObject.create("11111111111"));

        Assertions.assertEquals(exception.getMessage(), expectedMessageError);
    }

    @Test
    public void shouldNotCreateACPFIfDigitsIsBlank() {
        final String expectedMessageError = "Invalid CPF";
        final var exception = Assertions.assertThrows(DomainException.class, () -> CPFValueObject.create(""));

        Assertions.assertEquals(exception.getMessage(), expectedMessageError);
    }

    @Test
    public void shouldNotCreateIfCPFIsInvalid() {
        final String expectedMessageError = "Invalid CPF";
        final var exception = Assertions.assertThrows(DomainException.class, () -> CPFValueObject.create("59138058099"));

        Assertions.assertEquals(exception.getMessage(), expectedMessageError);
    }
}
