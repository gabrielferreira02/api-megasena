package com.gabrielferreira02.megasena.config;

import com.gabrielferreira02.megasena.Entity.Jogo;
import com.gabrielferreira02.megasena.repository.JogoRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final JogoRepository jogoRepository;

    @Override
    public void run(String... args) throws Exception {
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
                        .build(),

                Jogo.builder()
                        .cpf("831.525.330-12")
                        .nome("Ana Costa")
                        .numeros(List.of(8, 17, 25, 33, 44, 55))
                        .build(),

                Jogo.builder()
                        .cpf("557.442.772-93")
                        .nome("Carlos Pereira")
                        .numeros(List.of(2, 13, 28, 37, 46, 60))
                        .build(),

                Jogo.builder()
                        .cpf("022.076.430-18")
                        .nome("Fernanda Lima")
                        .numeros(List.of(9, 16, 24, 38, 49, 52))
                        .build(),

                Jogo.builder()
                        .cpf("922.572.590-68")
                        .nome("Ricardo Alves")
                        .numeros(List.of(4, 14, 26, 32, 43, 57))
                        .build(),

                Jogo.builder()
                        .cpf("249.233.740-55")
                        .nome("Juliana Martins")
                        .numeros(List.of(10, 18, 27, 39, 48, 53))
                        .build(),

                Jogo.builder()
                        .cpf("302.306.690-69")
                        .nome("Roberto Ferreira")
                        .numeros(List.of(1, 19, 30, 31, 50, 54))
                        .build(),

                Jogo.builder()
                        .cpf("568.573.040-33")
                        .nome("Patrícia Rocha")
                        .numeros(List.of(6, 20, 21, 40, 41, 51))
                        .build()
        );

        jogoRepository.saveAll(jogos);
    }
}
