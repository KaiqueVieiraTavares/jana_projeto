package org.example.jana_projeto_desafio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jana_projeto_desafio.enums.Perfil;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    //locais cadastrados (admin)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Local> locaisCriados = new ArrayList<>();
    //recursos cadastrados (admin)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Recurso> recursosCriados = new ArrayList<>();

    //recursos que foram agendados pelo usuario
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Agendamento> agendamentos = new ArrayList<>();

}
