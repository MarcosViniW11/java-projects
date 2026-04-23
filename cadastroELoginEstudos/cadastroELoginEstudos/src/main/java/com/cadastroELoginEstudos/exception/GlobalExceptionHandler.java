package com.cadastroELoginEstudos.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import com.cadastroELoginEstudos.dto.response.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApi(
            ApiException ex,
            HttpServletRequest request
    ){
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErrorResponse(
                        LocalDateTime.now(),
                        ex.getStatus(),
                        ex.getMessage(),
                        request.getRequestURI()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(
            Exception ex,
            HttpServletRequest request
    ) {
        return ResponseEntity.status(500)
                .body(new ErrorResponse(
                        LocalDateTime.now(),
                        500,
                        "Erro interno",
                        request.getRequestURI()
                ));
    }

}
