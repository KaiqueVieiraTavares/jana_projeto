package org.example.jana_projeto_desafio.services;

import org.example.jana_projeto_desafio.dtos.local.LocalCreateDTO;
import org.example.jana_projeto_desafio.dtos.local.LocalResponseDTO;
import org.example.jana_projeto_desafio.exceptions.usuario.UsuarioNaoEncontradoException;
import org.example.jana_projeto_desafio.model.Local;
import org.example.jana_projeto_desafio.repostiories.LocalRepository;
import org.example.jana_projeto_desafio.repostiories.UsuarioRepository;
import org.example.jana_projeto_desafio.security.SecurityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;

public class LocalService {
    private final LocalRepository localRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;
    public LocalService(LocalRepository localRepository, UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.localRepository = localRepository;
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public LocalResponseDTO createLocal(LocalCreateDTO localCreateDTO){
        Integer userID = SecurityUtils.getUserId();
        var user = usuarioRepository.findById(userID).orElseThrow(() -> new UsuarioNaoEncontradoException
                ("Usuario com id: " + userID + " nao encontrado!"));
        var local = modelMapper.map(localCreateDTO, Local.class);
        local.setus
    }
}
