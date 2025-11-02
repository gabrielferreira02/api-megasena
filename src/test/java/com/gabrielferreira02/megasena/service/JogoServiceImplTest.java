package com.gabrielferreira02.megasena.service;

import com.gabrielferreira02.megasena.Entity.Jogo;
import com.gabrielferreira02.megasena.dto.JogoRequest;
import com.gabrielferreira02.megasena.dto.JogoResponse;
import com.gabrielferreira02.megasena.dto.ResultadoResponse;
import com.gabrielferreira02.megasena.repository.JogoRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JogoServiceImplTest {

    @Mock
    private JogoRepository jogoRepository;
    @Mock
    private GerarNumeros gerarNumeros;
    @InjectMocks
    private JogoServiceImpl jogoService;

    @Test
    void deveRegistrarJogoComSucesso() {
        JogoRequest request = new JogoRequest("Gabriel", "886.221.440-54", List.of(1,2,3,4,5,6));

        JogoResponse response = jogoService.registrar(request);

        assertNotNull(response);
        assertEquals(request.nome(), response.nome());
        assertEquals(request.cpf(), response.cpf());
        assertEquals(request.numeros(), response.numeros());
    }

    @Test
    void deveFalharAoRegistrarJogoComNomeInvalido() {
        JogoRequest request = new JogoRequest("", "886.221.440-54", List.of(1,2,3,4,5,6));

        assertThrows(IllegalArgumentException.class, () -> {
            jogoService.registrar(request);
        });
    }

    @Test
    void deveFalharAoRegistrarJogoComCpfVazio() {
        JogoRequest request = new JogoRequest("Gabriel", "", List.of(1,2,3,4,5,6));

        assertThrows(IllegalArgumentException.class, () -> {
            jogoService.registrar(request);
        });
    }

    @Test
    void deveFalharAoRegistrarJogoComNumerosRepetidos() {
        JogoRequest request = new JogoRequest("Gabriel", "886.221.440-54", List.of(1,1,3,4,5,6));

        assertThrows(IllegalArgumentException.class, () -> {
            jogoService.registrar(request);
        });
    }

    @Test
    void deveFalharAoRegistrarJogoComNumerosFaltando() {
        JogoRequest request = new JogoRequest("Gabriel", "886.221.440-54", List.of(1,1,3,4,5));

        assertThrows(IllegalArgumentException.class, () -> {
            jogoService.registrar(request);
        });
    }

    @Test
    void deveGerarJogoComSucessoComVencedorNaSena() {
        List<Jogo> jogos = List.of(
                Jogo.builder()
                        .cpf("691.049.040-10")
                        .nome("João Silva")
                        .numeros(List.of(5, 12, 23, 34, 45, 56))
                        .build(),

                Jogo.builder()
                        .cpf("886.221.440-54")
                        .nome("Maria Santos")
                        .numeros(List.of(7, 15, 22, 35, 42, 58))
                        .build(),

                Jogo.builder()
                        .cpf("349.249.720-96")
                        .nome("Pedro Oliveira")
                        .numeros(List.of(3, 11, 29, 36, 47, 59))
                        .build()
        );

        List<Integer> numeros = List.of(3, 11, 29, 36, 47, 59);
        when(jogoRepository.findAll()).thenReturn(jogos);
        when(gerarNumeros.gerarNumeros()).thenReturn(numeros);

        ResultadoResponse response = jogoService.jogar();

        assertNotNull(response);
        assertEquals(1, response.vencedoresSena().size());
        assertEquals("Pedro Oliveira", response.vencedoresSena().getFirst().nome());
    }

    @Test
    void deveGerarJogoComSucessoComVencedorNaQuina() {
        List<Jogo> jogos = List.of(
                Jogo.builder()
                        .cpf("691.049.040-10")
                        .nome("João Silva")
                        .numeros(List.of(5, 12, 23, 34, 45, 56))
                        .build(),

                Jogo.builder()
                        .cpf("886.221.440-54")
                        .nome("Maria Santos")
                        .numeros(List.of(7, 15, 22, 35, 42, 58))
                        .build(),

                Jogo.builder()
                        .cpf("349.249.720-96")
                        .nome("Pedro Oliveira")
                        .numeros(List.of(3, 11, 29, 36, 47, 59))
                        .build()
        );

        List<Integer> numeros = List.of(3, 11, 29, 36, 47, 8);
        when(jogoRepository.findAll()).thenReturn(jogos);
        when(gerarNumeros.gerarNumeros()).thenReturn(numeros);

        ResultadoResponse response = jogoService.jogar();

        assertNotNull(response);
        assertEquals(1, response.vencedoresQuina().size());
        assertEquals("Pedro Oliveira", response.vencedoresQuina().getFirst().nome());
    }

    @Test
    void deveGerarJogoComSucessoComVencedorNaQuadra() {
        List<Jogo> jogos = List.of(
                Jogo.builder()
                        .cpf("691.049.040-10")
                        .nome("João Silva")
                        .numeros(List.of(5, 12, 23, 34, 45, 56))
                        .build(),

                Jogo.builder()
                        .cpf("886.221.440-54")
                        .nome("Maria Santos")
                        .numeros(List.of(7, 15, 22, 35, 42, 58))
                        .build(),

                Jogo.builder()
                        .cpf("349.249.720-96")
                        .nome("Pedro Oliveira")
                        .numeros(List.of(3, 11, 29, 36, 47, 59))
                        .build()
        );

        List<Integer> numeros = List.of(3, 11, 29, 36, 5, 8);
        when(jogoRepository.findAll()).thenReturn(jogos);
        when(gerarNumeros.gerarNumeros()).thenReturn(numeros);

        ResultadoResponse response = jogoService.jogar();

        assertNotNull(response);
        assertEquals(1, response.vencedoresQuadra().size());
        assertEquals("Pedro Oliveira", response.vencedoresQuadra().getFirst().nome());
    }
}