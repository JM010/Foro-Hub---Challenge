package com.JCservicios.forohub.infra.exeptions;

import com.JCservicios.forohub.domain.exception.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GestorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> error404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DatosErroresValidacion>> error400(MethodArgumentNotValidException e){
        var error = e.getFieldErrors();

        return  ResponseEntity.badRequest().body(error.stream().map(DatosErroresValidacion::new).toList());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> error400(ValidationException e){
        return  ResponseEntity.badRequest().body(e.getMessage());
    }

    public record DatosErroresValidacion(
            String campo,
            String mensaje)
    {



        public DatosErroresValidacion (FieldError error){
            this(
                    error.getField().contains(".") ?
                            error.getField().split("\\.")[1]
                            : error.getField(),
                    error.getDefaultMessage()
            );
        }

    }
}
