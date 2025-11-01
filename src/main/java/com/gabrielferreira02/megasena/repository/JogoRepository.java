package com.gabrielferreira02.megasena.repository;

import com.gabrielferreira02.megasena.Entity.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JogoRepository extends JpaRepository<Jogo, UUID> {
}
