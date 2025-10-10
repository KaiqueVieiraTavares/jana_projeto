package org.example.jana_projeto_desafio.model;

import jakarta.persistence.*;
import org.example.jana_projeto_desafio.enums.Periodo;
import org.example.jana_projeto_desafio.enums.Status;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Deslocamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deslocamentoId;
    @Column(name = "data_retirada")
    private LocalDate dataRetirada;
    @Enumerated(EnumType.STRING)
    private Periodo periodo;
    @Column(name = "hora_retirada")
    private LocalTime horaRetirada;
    @Column(name = "hora_retorno")
    private LocalTime horaRetorno;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "status_entrega")
    private boolean statusEntrega;

    @ManyToOne
    @JoinColumn(name = "agendamento_id")
    private Agendamento agendamento;

}
