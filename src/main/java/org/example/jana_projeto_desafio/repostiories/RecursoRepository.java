package org.example.jana_projeto_desafio.repostiories;

import org.example.jana_projeto_desafio.model.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Integer> {


}
