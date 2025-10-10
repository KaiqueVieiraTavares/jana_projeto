package org.example.jana_projeto_desafio.model;

import jakarta.persistence.*;
import org.example.jana_projeto_desafio.enums.Tipo;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer localId;
    @OneToMany(mappedBy = "local")
    private List<Usuario> usuarios = new ArrayList<>();
    private String local;
    private Tipo tipo;
}
