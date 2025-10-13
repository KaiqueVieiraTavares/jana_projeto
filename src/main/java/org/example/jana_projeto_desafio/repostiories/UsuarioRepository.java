package org.example.jana_projeto_desafio.repostiories;

import org.example.jana_projeto_desafio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);

    Boolean existsByEmail(String email);
}
