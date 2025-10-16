package org.example.jana_projeto_desafio.repostiories;

import org.example.jana_projeto_desafio.model.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalRepository extends JpaRepository<Local, Integer> {

    Integer local(String local);

    List<Local> local(String local);
}
