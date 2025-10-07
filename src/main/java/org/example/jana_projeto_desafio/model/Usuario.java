package org.example.jana_projeto_desafio.model;

import jakarta.persistence.*;
import org.example.jana_projeto_desafio.enums.Perfil;

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
    private Perfil perfll;
}
