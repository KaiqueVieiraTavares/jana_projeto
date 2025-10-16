package org.example.jana_projeto_desafio.services;

import org.example.jana_projeto_desafio.dtos.agendamento.AgendamentoCreateDTO;
import org.example.jana_projeto_desafio.dtos.agendamento.AgendamentoResponseDTO;
import org.example.jana_projeto_desafio.dtos.agendamento.AgendamentoUpdateDTO;
import org.example.jana_projeto_desafio.exceptions.agendamento.AgendamentoJaExisteException;
import org.example.jana_projeto_desafio.exceptions.agendamento.AgendamentoNaoEncontradoException;
import org.example.jana_projeto_desafio.exceptions.local.LocalNaoEncontradoException;
import org.example.jana_projeto_desafio.exceptions.recurso.RecursoNaoEncontradoException;
import org.example.jana_projeto_desafio.exceptions.usuario.UsuarioNaoEncontradoException;
import org.example.jana_projeto_desafio.model.Agendamento;
import org.example.jana_projeto_desafio.model.Local;
import org.example.jana_projeto_desafio.model.Recurso;
import org.example.jana_projeto_desafio.model.Usuario;
import org.example.jana_projeto_desafio.repostiories.AgendamentoRepository;
import org.example.jana_projeto_desafio.repostiories.LocalRepository;
import org.example.jana_projeto_desafio.repostiories.RecursoRepository;
import org.example.jana_projeto_desafio.repostiories.UsuarioRepository;
import org.example.jana_projeto_desafio.security.SecurityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AgendamentoService {
    private final AgendamentoRepository agendamentoRepository;
    private final ModelMapper modelMapper;
    private final RecursoRepository recursoRepository;
    private final LocalRepository localRepository;
    private final UsuarioRepository usuarioRepository;
    public AgendamentoService(AgendamentoRepository agendamentoRepository, ModelMapper modelMapper, RecursoRepository recursoRepository, LocalRepository localRepository, UsuarioRepository usuarioRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.modelMapper = modelMapper;
        this.recursoRepository = recursoRepository;
        this.localRepository = localRepository;
        this.usuarioRepository = usuarioRepository;
    }


    @Transactional
    public AgendamentoResponseDTO createAgendamento(AgendamentoCreateDTO agendamentoCreateDTO){
        Integer usuarioId = SecurityUtils.getUserId();
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException
                    ("Usuario com id: " + usuarioId + " não encontrado!"));
        Recurso recurso = recursoRepository.findById(agendamentoCreateDTO.recursoId()).orElseThrow(() ->
                new RecursoNaoEncontradoException("Recurso com id: " + agendamentoCreateDTO.recursoId() + " não encontrado!"));
        Local local = localRepository.findById(agendamentoCreateDTO.localId()).orElseThrow(()-> new LocalNaoEncontradoException
                ("Local com id: " + agendamentoCreateDTO.localId() + " não encontrado!"));

        agendamentoRepository.findAgendamentoByRecursoAndDataReservada(
                recurso, agendamentoCreateDTO.dataReservada()).ifPresent(a -> {
            throw new AgendamentoJaExisteException("Já existe um agendamento na data: " + agendamentoCreateDTO.dataReservada() + "!");
        });

        Agendamento agendamento = Agendamento.builder().recurso(recurso).local(local).dataReservada(agendamentoCreateDTO.dataReservada()).usuario(usuario).build();

        var savedAgendamento = agendamentoRepository.save(agendamento);
        return modelMapper.map(savedAgendamento, AgendamentoResponseDTO.class);
    }
    @PreAuthorize("hasRole('ADMIN') or @securityUtils.ehDonoDoAgendamento(#agendamentoId)")
    public void deleteAgendamento(Integer agendamentoId){
        agendamentoRepository.deleteById(agendamentoId);
    }
    @PreAuthorize("hasRole('ADMIN') or @securityUtils.ehDonoDoAgendamento(#agendamentoId)")
    public AgendamentoResponseDTO getAgendamento(Integer agendamentoId){
        Agendamento agendamento = agendamentoRepository.findById(agendamentoId)
                .orElseThrow(() -> new AgendamentoNaoEncontradoException(
                        "Agendamento com id " + agendamentoId + " não encontrado!"));
        return modelMapper.map(agendamento, AgendamentoResponseDTO.class);
    }

    //apenas admin ou o proprio usuario acessa este método
    @PreAuthorize("hasRole('ADMIN') or isAuthenticated()")
    public List<AgendamentoResponseDTO> getAllAgendamentosPorUsuario(){
        return agendamentoRepository.findAllByUsuarioId(SecurityUtils.getUserId()).stream()
                .map(agendamento -> modelMapper.map(agendamento, AgendamentoResponseDTO.class)).toList();
    }
    @PreAuthorize("hasRole('ADMIN') or @securityUtils.ehDonoDoAgendamento(#agendamentoId)")
    public AgendamentoResponseDTO updateAgendamento(Integer agendamentoId, AgendamentoUpdateDTO agendamentoUpdateDTO){
        var agendamento = agendamentoRepository.findById(agendamentoId).orElseThrow(() -> new AgendamentoNaoEncontradoException
                ("Agendamento com id: " + agendamentoId + " não encontrado!"));
        modelMapper.map(agendamentoUpdateDTO, agendamento);
        var savedAgendamento = agendamentoRepository.save(agendamento);
        return modelMapper.map(savedAgendamento, AgendamentoResponseDTO.class);
    }
}
