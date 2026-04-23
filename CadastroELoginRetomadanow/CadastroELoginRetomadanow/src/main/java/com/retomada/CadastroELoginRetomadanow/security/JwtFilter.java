package com.retomada.CadastroELoginRetomadanow.security;

import com.retomada.CadastroELoginRetomadanow.entity.Usuario;
import com.retomada.CadastroELoginRetomadanow.exception.excessao;
import com.retomada.CadastroELoginRetomadanow.repoitory.UsuarioRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UsuarioRepository usuarioRepository;
    public JwtFilter(JwtService jwtService, UsuarioRepository usuarioRepository) {
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try{
            Claims claims = jwtService.validarToken(token);
            String email = claims.getSubject();

            Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(()->new excessao("Token Invalido"));

            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+usuario.getRole());

            Authentication authentication = new UsernamePasswordAuthenticationToken(usuario.getEmail(), null, List.of(authority));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);

    }

}
