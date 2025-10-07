package org.example.jana_projeto_desafio.dtos.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.example.jana_projeto_desafio.enums.Perfil;

public record UsuarioCreateDto(@NotNull int matricula, @NotBlank String nome, @NotBlank @Email String email,
                               @NotBlank @Pattern(regexp = "^(?=.*[a-z]) (?=.*[A-Z]) (?=.*[0-9]) (?=.*[!@#$%&*¨]) .{8,}$",
                                       message = "A senha deve conter pelo menos uma letra minuscula, uma letra maiuscula, um numero, um caractere especial" +
                                               " e 8 caracteres!" )
                               String senha_hash, Perfil perfil) {
}
