package org.example.jana_projeto_desafio.services;

import org.example.jana_projeto_desafio.dtos.usuario.UsuarioResponseDto;
import org.example.jana_projeto_desafio.dtos.usuario.UsuarioUpdateDto;
import org.example.jana_projeto_desafio.exceptions.UserNotFoundException;
import org.example.jana_projeto_desafio.repostiories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;
    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    public List<UsuarioResponseDto> getAllUsers(){
        return usuarioRepository.findAll().stream().map(usuario -> modelMapper.map(usuario, UsuarioResponseDto.class)).toList();
    }
    public void deleteUser(Integer id){
        var user = usuarioRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuario com id: " + id + " não encontrado"));
        usuarioRepository.delete(user);
    }
    public UsuarioResponseDto updateUser(Integer id, UsuarioUpdateDto usuarioUpdateDto){
        var user = usuarioRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuario com id: " + id + " não encontrado!"));
        modelMapper.map(usuarioUpdateDto, user);
       var savedUser = usuarioRepository.save(user);
       return modelMapper.map(savedUser, UsuarioResponseDto.class);
    }
    public UsuarioResponseDto getUser(Integer id){
        var user = usuarioRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuario com id: " + id + " não encontrado!"));
        return modelMapper.map(user, UsuarioResponseDto.class);
    }
}
