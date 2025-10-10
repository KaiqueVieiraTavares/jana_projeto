package org.example.jana_projeto_desafio.model;

import jakarta.persistence.*;
import org.example.jana_projeto_desafio.enums.Tipo;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Recurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recurso_id;
    private int codPat;
    @Enumerated(EnumType.STRING)
    private Tipo recurso;
    private String marca;
    private int numero;
    private boolean funcional;
    private String observacao;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @OneToMany(mappedBy = "recurso", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Agendamento> agendamentos = new ArrayList<>();


    @OneToMany(mappedBy = "recurso", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Registro> registros = new ArrayList<>();

}
