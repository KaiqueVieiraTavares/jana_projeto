package org.example.jana_projeto_desafio.security;

import io.jsonwebtoken.security.Password;
import org.example.jana_projeto_desafio.enums.Perfil;
import org.example.jana_projeto_desafio.exceptions.usuario.EmailJaExisteException;
import org.example.jana_projeto_desafio.model.Usuario;
import org.example.jana_projeto_desafio.repostiories.UsuarioRepository;
import org.example.jana_projeto_desafio.security.dtos.RegisterDTO;
import org.example.jana_projeto_desafio.security.dtos.UsuarioResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    public AuthService(UsuarioRepository usuarioRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }
    public UsuarioResponseDTO registerUser(RegisterDTO registerDTO){
        if(usuarioRepository.existsByEmail(registerDTO.email())){
            throw new EmailJaExisteException("O email: " + registerDTO.email() + " ja existe!");
        }
        var user = Usuario.builder().email(registerDTO.email()).nome(registerDTO.nome()).matricula(registerDTO.matricula()).
                perfil(Perfil.COMUM).senhaHash(passwordEncoder.encode(registerDTO.senha())).build();
        var savedUser = usuarioRepository.save(user);
        return modelMapper.map(savedUser, UsuarioResponseDTO.class);
    }
}
