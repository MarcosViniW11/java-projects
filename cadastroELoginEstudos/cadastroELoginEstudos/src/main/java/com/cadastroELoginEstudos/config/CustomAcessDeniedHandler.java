package com.cadastroELoginEstudos.config;

import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.*;
import org.springframework.security.access.AccessDeniedException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@Component
public class CustomAcessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper mapper;

    public CustomAcessDeniedHandler(ObjectMapper mapper){
        this.mapper=mapper;
    }

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException
    ) throws IOException{

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        Map<String, Object> body = Map.of(
                "timestamp", LocalDateTime.now(),
                "status",403,
                "error","Acesso negado",
                "path",request.getRequestURI()
        );

        mapper.writeValue(response.getOutputStream(), body);

    }


}
