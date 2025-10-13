package org.example.jana_projeto_desafio.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    public static Integer getUserId(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth==null || !auth.isAuthenticated()){
            return null;
        }
        var userDetails = (CustomUserDetails) auth.getPrincipal();
        return userDetails.getId();
    }
}
