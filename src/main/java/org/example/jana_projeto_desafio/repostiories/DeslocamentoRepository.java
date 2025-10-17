package org.example.jana_projeto_desafio.repostiories;

import org.example.jana_projeto_desafio.model.Deslocamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeslocamentoRepository extends JpaRepository<Deslocamento, Integer> {
}
