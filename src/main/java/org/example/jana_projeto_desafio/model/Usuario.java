package org.example.jana_projeto_desafio.model;

import jakarta.persistence.*;
import org.example.jana_projeto_desafio.enums.Perfil;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int matricula;
    private String nome;
    private String email;
    @Column(name = "senha_hash")
    private String senhaHash;
    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Recurso> recursos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "local_id")
    private Local local;
}
