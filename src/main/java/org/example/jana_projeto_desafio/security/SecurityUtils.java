package org.example.jana_projeto_desafio.security;

import org.example.jana_projeto_desafio.exceptions.agendamento.AgendamentoNaoEncontradoException;
import org.example.jana_projeto_desafio.repostiories.AgendamentoRepository;
import org.example.jana_projeto_desafio.repostiories.UsuarioRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;



@Component
public class SecurityUtils {
    private final AgendamentoRepository agendamentoRepository;
    private final UsuarioRepository usuarioRepository;
    public SecurityUtils(AgendamentoRepository agendamentoRepository, UsuarioRepository usuarioRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public static Integer getUserId(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var userDetails = (CustomUserDetails) auth.getPrincipal();
        return userDetails.getId();
    }

    public boolean ehDonoDoAgendamento(Integer agendamentoId)  {
        var usuarioId = getUserId();
        var agendamento = agendamentoRepository.findById(agendamentoId)
                .orElseThrow(() -> new AgendamentoNaoEncontradoException(
                        "Agendamento com id: " + agendamentoId + " não encontrado!"));
        return agendamento.getUsuario().getId().equals(usuarioId);
    }
}
