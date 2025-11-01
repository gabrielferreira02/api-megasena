package com.gabrielferreira02.megasena.controller;

import com.gabrielferreira02.megasena.dto.ErroResponse;
import com.gabrielferreira02.megasena.dto.JogoRequest;
import com.gabrielferreira02.megasena.dto.JogoResponse;
import com.gabrielferreira02.megasena.dto.ResultadoResponse;
import com.gabrielferreira02.megasena.service.JogoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@AllArgsConstructor
public class JogoController {

    private final JogoService jogoService;

    @Operation(
            summary = "Retorna o resultado da megasena",
            description = "Endpoint responsável por retornar o resultado da megasena. No corpo da resposta vêm informado os números sorteados, a premiação por pessoa para cada nível(sena,quina,quadra). Caso não tenham ganhadores no nível o prêmio será 0. É também, retornada a lista de vencedores de cada nível",
            tags = "megasena",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Resultado retornado com sucesso",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ResultadoResponse.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Erro interno do servidor"
                    )
            }
    )
    @GetMapping("jogar")
    public ResponseEntity<ResultadoResponse> jogar() {
        ResultadoResponse resultado = jogoService.jogar();
        return ResponseEntity.ok(resultado);
    }

    @Operation(
            summary = "Registra um novo jogo na megasena",
            description = "Endpoint responsável por registrar o jogo de um usuário para concorrer a megasena",
            tags = "megasena",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Jogo registrado com sucesso",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = JogoResponse.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro no corpo da requisição",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErroResponse.class
                                    )
                            )
                    )
            }
    )
    @PostMapping("registrar")
    public ResponseEntity<JogoResponse> registrar(@RequestBody JogoRequest request) {
        JogoResponse resposta = jogoService.registrar(request);
        return ResponseEntity.ok(resposta);
    }
}
