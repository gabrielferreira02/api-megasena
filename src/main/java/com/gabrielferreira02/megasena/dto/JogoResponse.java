package com.gabrielferreira02.megasena.dto;

import java.util.List;

public record JogoResponse(String nome,
                           String cpf,
                           List<Integer> numeros) { }
