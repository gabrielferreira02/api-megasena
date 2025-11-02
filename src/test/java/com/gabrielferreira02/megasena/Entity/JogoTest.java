package com.gabrielferreira02.megasena.Entity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JogoTest {

    private final Validator validator;

    public JogoTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    public void deveCriarOJogoComSucesso() {
        String nome = "Gabriel";
        String cpf = "886.221.440-54";
        var numeros = List.of(1,2,3,4,5,6);

        Jogo jogo = new Jogo(null, nome, cpf, numeros);

        assertEquals(nome, jogo.getNome());
        assertEquals(cpf, jogo.getCpf());
        assertEquals(numeros, jogo.getNumeros());
    }

    @Test
    public void deveFalharAoCriarJogoComCpfInvalido() {
        String nome = "Gabriel";
        String cpf = "886.221.440-03";
        var numeros = List.of(1,2,3,4,5,6);

        Jogo jogo = new Jogo(null, nome, cpf, numeros);
        Set<ConstraintViolation<Jogo>> violacoes = validator.validate(jogo);

        assertFalse(violacoes.isEmpty());
        assertTrue(violacoes.stream()
                .anyMatch(v -> v.getMessage().equals("Cpf inválido")));
    }

    @Test
    public void deveFalharAoCriarJogoComNomeInvalido() {
        String nome = "";
        String cpf = "886.221.440-54";
        var numeros = List.of(1,2,3,4,5,6);

        Jogo jogo = new Jogo(null, nome, cpf, numeros);
        Set<ConstraintViolation<Jogo>> violacoes = validator.validate(jogo);

        assertFalse(violacoes.isEmpty());
    }

    @Test
    public void deveFalharAoCriarJogoComListaSem6Numeros() {
        String nome = "Gabriel";
        String cpf = "886.221.440-54";
        var numeros = List.of(1,2,3,4,5);

        Jogo jogo = new Jogo(null, nome, cpf, numeros);
        Set<ConstraintViolation<Jogo>> violacoes = validator.validate(jogo);

        assertFalse(violacoes.isEmpty());
    }
}