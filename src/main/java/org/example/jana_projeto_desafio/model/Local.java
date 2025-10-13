package org.example.jana_projeto_desafio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jana_projeto_desafio.enums.Tipo;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer localId;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private String local;
    private Tipo tipo;
    //um local pode ter varios recursos
    @OneToMany(mappedBy = "local")
    private List<Recurso> recursos = new ArrayList<>();
}
