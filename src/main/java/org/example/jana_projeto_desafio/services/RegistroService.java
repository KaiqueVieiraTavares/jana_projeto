package org.example.jana_projeto_desafio.services;

import org.example.jana_projeto_desafio.dtos.registro.RegistroCreateDTO;
import org.example.jana_projeto_desafio.dtos.registro.RegistroResponseDTO;
import org.example.jana_projeto_desafio.exceptions.registro.RegistroNaoEncontradoException;
import org.example.jana_projeto_desafio.model.Registro;
import org.example.jana_projeto_desafio.repostiories.RegistroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroService {
    private final RegistroRepository registroRepository;
    private final ModelMapper modelMapper;

    public RegistroService(RegistroRepository registroRepository, ModelMapper modelMapper) {
        this.registroRepository = registroRepository;
        this.modelMapper = modelMapper;
    }

    public void createRegistro(RegistroCreateDTO registroCreateDTO){
        registroRepository.save(modelMapper.map(registroCreateDTO, Registro.class));
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteRegistro(Integer registroId){
        var registro = registroRepository.findById(registroId).orElseThrow(() -> new RegistroNaoEncontradoException
                ("Registro com id: " + registroId + " não encontrado!"));
        registroRepository.delete(registro);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<RegistroResponseDTO> getAllRegistros(){
        return registroRepository.findAll().stream().map(registro -> modelMapper.map(registro, RegistroResponseDTO.class)).toList();
    }
}
