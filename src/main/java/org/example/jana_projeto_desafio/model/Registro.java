package org.example.jana_projeto_desafio.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer registroId;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private boolean funcional;
    private String observacao;
    @ManyToOne
    @JoinColumn(name = "local_id")
    private Local local;
    private Agendamento agendamento;
}
