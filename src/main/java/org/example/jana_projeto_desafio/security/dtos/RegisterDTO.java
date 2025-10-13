package org.example.jana_projeto_desafio.security.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.example.jana_projeto_desafio.enums.Perfil;

public record RegisterDTO(
        @NotNull(message = "A matrícula é obrigatória")
        Integer matricula,

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @Email(message = "Email inválido")
        @NotBlank(message = "O email é obrigatório")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%&*]).{8,}$",
                message = "A senha deve ter no mínimo 8 caracteres, incluindo letra maiúscula, minúscula, número e caractere especial"
        )
        String senha,

        @NotNull(message = "O perfil é obrigatório")
        Perfil perfil,

        @NotNull(message = "O ID do local é obrigatório")
        Integer localId
) {}
