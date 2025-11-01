package com.gabrielferreira02.megasena.dto;

import java.util.List;

public record ResultadoResponse(List<Integer> numeros,
                                double premioSena,
                                double premioQuina,
                                double premioQuadra,
                                List<Vencedores> vencedoresSena,
                                List<Vencedores> vencedoresQuina,
                                List<Vencedores> vencedoresQuadra) { }
