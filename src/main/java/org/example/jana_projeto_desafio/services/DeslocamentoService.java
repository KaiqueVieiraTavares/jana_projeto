package org.example.jana_projeto_desafio.services;

import org.example.jana_projeto_desafio.dtos.deslocamento.DeslocamentoResponseDTO;
import org.example.jana_projeto_desafio.dtos.deslocamento.DeslocamentoRetiradaDTO;
import org.example.jana_projeto_desafio.dtos.deslocamento.DeslocamentoRetornoDTO;
import org.example.jana_projeto_desafio.model.Deslocamento;
import org.example.jana_projeto_desafio.repostiories.DeslocamentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DeslocamentoService {
    private final DeslocamentoRepository deslocamentoRepository;
    private final ModelMapper modelMapper;
    public DeslocamentoService(DeslocamentoRepository deslocamentoRepository, ModelMapper modelMapper) {
        this.deslocamentoRepository = deslocamentoRepository;
        this.modelMapper = modelMapper;
    }

    public DeslocamentoResponseDTO registrarRetirada(DeslocamentoRetiradaDTO deslocamentoRetiradaDTO){
        var deslocamentoEntity = modelMapper.map(deslocamentoRetiradaDTO, Deslocamento.class);
        return modelMapper.map(deslocamentoRepository.save(deslocamentoEntity), DeslocamentoResponseDTO.class);
    }

    public DeslocamentoResponseDTO registrarRetorno(DeslocamentoRetornoDTO deslocamentoRetornoDTO){
        var deslocamentoEntity = modelMapper.map(deslocamentoRetornoDTO, Deslocamento.class);
        return modelMapper.map(deslocamentoRepository.save(deslocamentoEntity), DeslocamentoResponseDTO.class);
    }

    public List<DeslocamentoResponseDTO> getAllDeslocamentos(){
        return deslocamentoRepository.findAll().stream().map(registro -> modelMapper.map(registro, DeslocamentoResponseDTO.class)).toList();
    }
}
