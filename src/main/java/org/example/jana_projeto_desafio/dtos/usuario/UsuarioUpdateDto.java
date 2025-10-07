package org.example.jana_projeto_desafio.dtos.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.jana_projeto_desafio.enums.Perfil;

public record UsuarioUpdateDto(@NotNull int matricula, @NotBlank String nome, @NotBlank @Email String email, Perfil perfil) {
}
