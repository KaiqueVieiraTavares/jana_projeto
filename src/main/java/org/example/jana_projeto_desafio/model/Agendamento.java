package org.example.jana_projeto_desafio.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer agendamentoId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;


    private LocalDate dataReservada;


    @ManyToOne
    @JoinColumn(name = "local_id", nullable = false)
    private Local local;


    @ManyToOne
    @JoinColumn(name = "recurso_id", nullable = false)
    private Recurso recurso;


    @OneToMany(mappedBy = "agendamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Deslocamento> deslocamentos = new ArrayList<>();


    @OneToMany(mappedBy = "agendamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Registro> registros = new ArrayList<>();
}
