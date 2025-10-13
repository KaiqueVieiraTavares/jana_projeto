package org.example.jana_projeto_desafio.security;

import org.example.jana_projeto_desafio.exceptions.usuario.EmailNaoEncontradoException;
import org.example.jana_projeto_desafio.repostiories.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       var usuario = usuarioRepository.findByEmail(username).orElseThrow(() -> new EmailNaoEncontradoException
               ("Email: " + username + " nao encontrado!"));
       return new CustomUserDetails(usuario);
    }
}
