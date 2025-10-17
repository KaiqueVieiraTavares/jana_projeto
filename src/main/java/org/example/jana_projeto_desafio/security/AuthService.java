package org.example.jana_projeto_desafio.security;

import org.example.jana_projeto_desafio.dtos.usuario.UsuarioResponseDto;
import org.example.jana_projeto_desafio.enums.Perfil;
import org.example.jana_projeto_desafio.exceptions.usuario.EmailJaExisteException;
import org.example.jana_projeto_desafio.exceptions.usuario.UsuarioNaoEncontradoException;
import org.example.jana_projeto_desafio.model.Usuario;
import org.example.jana_projeto_desafio.repostiories.UsuarioRepository;
import org.example.jana_projeto_desafio.security.dtos.LoginDTO;
import org.example.jana_projeto_desafio.security.dtos.RegisterDTO;
import org.example.jana_projeto_desafio.security.dtos.AuthResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    public AuthService(UsuarioRepository usuarioRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }
    public UsuarioResponseDto registerUser(RegisterDTO registerDTO){
        if(usuarioRepository.existsByEmail(registerDTO.email())){
            throw new EmailJaExisteException("O email: " + registerDTO.email() + " ja existe!");
        }
        var user = Usuario.builder().email(registerDTO.email()).nome(registerDTO.nome()).matricula(registerDTO.matricula()).
                perfil(Perfil.COMUM).senhaHash(passwordEncoder.encode(registerDTO.senha())).build();
        var savedUser = usuarioRepository.save(user);
        return modelMapper.map(savedUser, UsuarioResponseDto.class);
    }
    public AuthResponseDto loginUser(LoginDTO loginDTO){
        var authenticationCreate = new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.senha());
        var authentication = authenticationManager.authenticate(authenticationCreate);
        var userDetails = (CustomUserDetails) authentication.getPrincipal();
        var usuario = usuarioRepository.findById(userDetails.getId()).orElseThrow(() -> new UsuarioNaoEncontradoException
                ("Usuario com id: " + userDetails.getId() + " não encontrado!"));
        String token = tokenService.generateToken(usuario);
        return new AuthResponseDto(usuario.getId(),usuario.getNome(), usuario.getMatricula(),usuario.getEmail(),usuario.getPerfil(), token);
    }
}
