package com.example.demo.security;


import com.example.demo.entity.Usuario;
import com.example.demo.exception.Invalid;
import com.example.demo.repository.UsuarioRepository;
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

    public JwtFilter(JwtService jwtService,UsuarioRepository usuarioRepository){
        this.jwtService=jwtService;
        this.usuarioRepository=usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException{

        String header= request.getHeader("Authorization");
        if(header == null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        String token = header.substring(7);

        try{
            Claims claims = jwtService.validarToken(token);
            String email = claims.getSubject();

            Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(() -> new Invalid("Token invalido!"));

            GrantedAuthority authority=new SimpleGrantedAuthority("ROLE_"+usuario.getRole());

            Authentication auth = new UsernamePasswordAuthenticationToken(
                    usuario.getEmail(),
                    null,
                    List.of(authority)
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request,response);

    }

}
