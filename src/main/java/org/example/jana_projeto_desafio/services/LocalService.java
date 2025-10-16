package org.example.jana_projeto_desafio.services;

import org.example.jana_projeto_desafio.dtos.local.LocalCreateDTO;
import org.example.jana_projeto_desafio.dtos.local.LocalResponseDTO;
import org.example.jana_projeto_desafio.dtos.local.LocalUpdateDTO;
import org.example.jana_projeto_desafio.exceptions.local.LocalNaoEncontradoException;
import org.example.jana_projeto_desafio.exceptions.usuario.UsuarioNaoEncontradoException;
import org.example.jana_projeto_desafio.model.Local;
import org.example.jana_projeto_desafio.repostiories.LocalRepository;
import org.example.jana_projeto_desafio.repostiories.UsuarioRepository;
import org.example.jana_projeto_desafio.security.SecurityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
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
        local.setUsuario(user);
        var savedLocal = localRepository.save(local);
        return modelMapper.map(savedLocal, LocalResponseDTO.class);
    }
    public List<LocalResponseDTO> getAllLocais(){
        return localRepository.findAll().stream().map(local -> modelMapper.map(local, LocalResponseDTO.class)).toList();
    }
    public LocalResponseDTO getLocal(Integer localId){
        var local = localRepository.findById(localId).orElseThrow(() -> new LocalNaoEncontradoException("Local de id: " + localId + " não encontrado!"));
        return modelMapper.map(local, LocalResponseDTO.class);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteLocal(Integer localId){
        var local = localRepository.findById(localId).orElseThrow(() -> new LocalNaoEncontradoException("Local com id: " + localId + " não encontrado!"));
        localRepository.delete(local);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public LocalResponseDTO updateLocal(Integer localId, LocalUpdateDTO localUpdateDTO){
        var local = localRepository.findById(localId).orElseThrow(() -> new LocalNaoEncontradoException("Local com id: " + localId + " não encontrado!"));
        modelMapper.map(localUpdateDTO, local);
        var savedLocal = localRepository.save(local);
        return modelMapper.map(savedLocal, LocalResponseDTO.class);
    }
}
