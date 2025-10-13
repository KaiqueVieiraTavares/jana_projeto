package org.example.jana_projeto_desafio.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import org.example.jana_projeto_desafio.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenService {
    private final Key key;

    public TokenService(@Value("${api.secret}") String secret){
        byte[] bytes = secret.getBytes();
        this.key = Keys.hmacShaKeyFor(bytes);
    }

    public String generateToken(Usuario usuario){
        return Jwts.builder().issuedAt(Date.from(Instant.now())).expiration(Date.from(Instant.now().
                plus(3, ChronoUnit.HOURS))).subject(usuario.getEmail()).claim("perfil", usuario.getPerfil()).signWith(key).compact();
    }

    public String verifyToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody().getSubject();

        }catch (ExpiredJwtException e) {
        throw new RuntimeException("Token expirado" + e.getMessage());
    } catch (MalformedJwtException e) {
        throw new RuntimeException("Token malformado" + e.getMessage());
    } catch (Exception e) {
        throw new RuntimeException("Token inválido: " + e.getMessage());
    }
}
    }

