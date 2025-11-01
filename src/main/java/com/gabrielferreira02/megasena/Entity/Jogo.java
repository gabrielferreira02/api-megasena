package com.gabrielferreira02.megasena.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "jogos")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotEmpty
    private String nome;

    @CPF(message = "Cpf inválido")
    private String cpf;

    @Size(min = 6, max = 6)
    @ElementCollection
    @CollectionTable(name = "jogo_numeros", joinColumns = @JoinColumn(name = "jogo_id"))
    @Column(name = "numero")
    private List<Integer> numeros;
}
