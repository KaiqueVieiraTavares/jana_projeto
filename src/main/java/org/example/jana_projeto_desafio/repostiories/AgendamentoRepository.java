package org.example.jana_projeto_desafio.repostiories;

import org.example.jana_projeto_desafio.model.Agendamento;
import org.example.jana_projeto_desafio.model.Recurso;
import org.example.jana_projeto_desafio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
    Optional<Agendamento> findAgendamentoByRecursoAndDataReservada(Recurso recurso, LocalDate dataReservada);

    Optional<Agendamento> findByAgendamentoIdAndUsuario(Integer agendamentoId, Usuario usuario);

    List<Agendamento> findAllByUsuarioId(Integer usuarioId);
}
