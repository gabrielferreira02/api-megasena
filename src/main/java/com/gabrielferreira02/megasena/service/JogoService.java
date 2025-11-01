package com.gabrielferreira02.megasena.service;

import com.gabrielferreira02.megasena.dto.JogoRequest;
import com.gabrielferreira02.megasena.dto.JogoResponse;
import com.gabrielferreira02.megasena.dto.ResultadoResponse;

public interface JogoService {
    JogoResponse registrar(JogoRequest request);
    ResultadoResponse jogar();
}
