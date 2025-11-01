package com.gabrielferreira02.megasena.service;

import com.gabrielferreira02.megasena.Entity.Jogo;
import com.gabrielferreira02.megasena.dto.JogoRequest;
import com.gabrielferreira02.megasena.dto.JogoResponse;
import com.gabrielferreira02.megasena.dto.ResultadoResponse;
import com.gabrielferreira02.megasena.dto.Vencedores;
import com.gabrielferreira02.megasena.repository.JogoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class JogoServiceImpl implements JogoService{

    private final JogoRepository jogoRepository;
    private static final double premioTotal = 320000000.0;
    private static final Random random = new Random();

    @Override
    public JogoResponse registrar(JogoRequest request) {
        if (request.nome().isEmpty()) {
            log.warn("Erro ao registrar jogo, nome é vazio");
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }

        if(request.cpf().isEmpty()) {
            log.warn("Erro ao registrar jogo, cpf é vazio");
            throw new IllegalArgumentException("Cpf não pode ser vazio");
        }

        if(request.numeros().size() != 6) {
            log.warn("Erro ao registrar jogo, jogo precisa ter 6 números");
            throw new IllegalArgumentException("É necessário 6 números para jogar");
        }

        Set<Integer> numerosUnicos = new HashSet<>(request.numeros());
        if(numerosUnicos.size() != 6) {
            log.warn("Erro ao registrar jogo, contém números repetidos");
            throw new IllegalArgumentException("Jogo não pode conter números repetidos");
        }

        for(Integer numero:  request.numeros()) {
            if(numero > 60 || numero < 1) {
                log.warn("Erro ao registrar jogo, número tem que estar entre 1 e 60");
                throw new IllegalArgumentException("Os números jogados tem que estar entre 1 e 60");
            }
        }

        Jogo jogo = new Jogo(null, request.nome(), request.cpf(), request.numeros());
        jogoRepository.save(jogo);
        log.info("Jogo registrado com sucesso para o cpf: {}", jogo.getCpf());
        return new JogoResponse(request.nome(), request.cpf(), request.numeros());
    }

    @Override
    public ResultadoResponse jogar() {
        List<Jogo> jogos = jogoRepository.findAll();
        List<Integer> numerosSorteados = sortearNumeros();
        List<Vencedores> vencedores6Numeros = new ArrayList<>();
        List<Vencedores> vencedores5Numeros = new ArrayList<>();
        List<Vencedores> vencedores4Numeros = new ArrayList<>();

        for(Jogo jogo: jogos) {
            int acertos = calcularAcertos(jogo.getNumeros(), numerosSorteados);

            Vencedores vencedor = new Vencedores(jogo.getNome(), jogo.getCpf());
            if(acertos == 6) vencedores6Numeros.add(vencedor);
            if(acertos == 5) vencedores5Numeros.add(vencedor);
            if(acertos == 4) vencedores4Numeros.add(vencedor);
        }

        log.info("Resultado da megasena gerado com sucesso");
        return gerarResultado(numerosSorteados,
                vencedores6Numeros,
                vencedores5Numeros,
                vencedores4Numeros);
    }

    private static ResultadoResponse gerarResultado(List<Integer> numerosSorteados,
                                                    List<Vencedores> vencedoresSena,
                                                    List<Vencedores> vencedoresQuina,
                                                    List<Vencedores> vencedoresQuadra) {
        double premioSena = vencedoresSena.isEmpty() ? 0 : (premioTotal * 0.40) / (double) vencedoresSena.size();
        double premioQuina = vencedoresQuina.isEmpty() ? 0 : (premioTotal * 0.13) / (double) vencedoresQuina.size();
        double premioQuadra = vencedoresQuadra.isEmpty() ? 0 : (premioTotal * 0.15) / (double) vencedoresQuadra.size();

        return new ResultadoResponse(numerosSorteados,
                premioSena,
                premioQuina,
                premioQuadra,
                vencedoresSena,
                vencedoresQuina,
                vencedoresQuadra);
    }

    private static List<Integer> sortearNumeros() {
        List<Integer> numeros = new ArrayList<>();

        while(numeros.size() < 6) {
            int numero = random.nextInt(60) + 1;
            if(!numeros.contains(numero)) numeros.add(numero);
        }

        return numeros;
    }

    private static int calcularAcertos(List<Integer> numerosJogados, List<Integer> numerosSorteados) {
        return (int) numerosJogados.stream().filter(numerosSorteados::contains).count();
    }
}
