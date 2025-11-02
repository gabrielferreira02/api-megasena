package com.gabrielferreira02.megasena.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class GerarNumerosImpl implements GerarNumeros{
    private static final Random random = new Random();

    @Override
    public List<Integer> gerarNumeros() {
        List<Integer> numeros = new ArrayList<>();

        while(numeros.size() < 6) {
            int numero = random.nextInt(60) + 1;
            if(!numeros.contains(numero)) numeros.add(numero);
        }

        return numeros;
    }
}
