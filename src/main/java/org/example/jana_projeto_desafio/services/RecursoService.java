package org.example.jana_projeto_desafio.services;

import org.example.jana_projeto_desafio.dtos.recurso.RecursoCreateDTO;
import org.example.jana_projeto_desafio.dtos.recurso.RecursoResponseDTO;
import org.example.jana_projeto_desafio.dtos.recurso.RecursoUpdateDTO;
import org.example.jana_projeto_desafio.exceptions.recurso.RecursoNaoEncontradoException;
import org.example.jana_projeto_desafio.exceptions.usuario.UsuarioNaoEncontradoException;
import org.example.jana_projeto_desafio.model.Recurso;
import org.example.jana_projeto_desafio.repostiories.RecursoRepository;
import org.example.jana_projeto_desafio.repostiories.UsuarioRepository;
import org.example.jana_projeto_desafio.security.SecurityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecursoService {
    private final RecursoRepository recursoRepository;
    private final ModelMapper modelMapper;
    private final UsuarioRepository usuarioRepository;
    public RecursoService(RecursoRepository recursoRepository, ModelMapper modelMapper, UsuarioRepository usuarioRepository) {
        this.recursoRepository = recursoRepository;
        this.modelMapper = modelMapper;
        this.usuarioRepository = usuarioRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public RecursoResponseDTO createRecurso (RecursoCreateDTO recursoCreateDTO){
        Integer idUsuario = SecurityUtils.getUserId();
        Recurso recursoEntity = modelMapper.map(recursoCreateDTO, Recurso.class);var user = usuarioRepository.findById
                (idUsuario).orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario com id: " + idUsuario + " não encontrado!"));
        recursoEntity.setUsuario(user);
        var savedRecursoEntity = recursoRepository.save(recursoEntity);
        return modelMapper.map(savedRecursoEntity, RecursoResponseDTO.class);
    }
    public RecursoResponseDTO getRecurso(Integer recursoId){
        Recurso recurso = recursoRepository.findById(recursoId).orElseThrow(() -> new RecursoNaoEncontradoException
                ("Recurso com id: " + recursoId + " nao encotrado!"));
        return modelMapper.map(recurso, RecursoResponseDTO.class);
    }

    public List<RecursoResponseDTO> getAllRecursos(){
        return recursoRepository.findAll().stream().map(recurso -> modelMapper.map(recurso, RecursoResponseDTO.class)).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteRecurso(Integer recursoId){
        Recurso recurso = recursoRepository.findById(recursoId).orElseThrow(() -> new RecursoNaoEncontradoException
                ("Recurso com id: " + recursoId + " nao encontrado!"));
        recursoRepository.delete(recurso);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public RecursoResponseDTO updateRecurso(Integer recursoId, RecursoUpdateDTO recursoUpdateDTO){
        var recurso = recursoRepository.findById(recursoId).orElseThrow(() -> new RecursoNaoEncontradoException
                ("Recurso com id: " + recursoId + " nao encontrado!"));
        modelMapper.map(recursoUpdateDTO, recurso);
        var recursoSalvo = recursoRepository.save(recurso);
        return modelMapper.map(recursoSalvo, RecursoResponseDTO.class);
    }
}
