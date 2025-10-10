package org.example.jana_projeto_desafio.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer agendamentoId;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private LocalDate dataReservada;

    @ManyToOne()
    @JoinColumn(name = "local_id")
    private Local local;

    @OneToMany(mappedBy = "agendamento", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Deslocamento> deslocamentos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "recurso_id")
    private Recurso recurso;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registro_id")
    private Registro registro;
}
