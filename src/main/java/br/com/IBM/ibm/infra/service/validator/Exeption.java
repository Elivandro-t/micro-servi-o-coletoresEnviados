package br.com.IBM.ibm.infra.service.validator;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotBlank;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class Exeption {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity Runtime(RuntimeException e){
        return ResponseEntity.ok().body(new ErroDto(e.getMessage()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroDto> handleValidationException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldError().getDefaultMessage();
        ErroDto errorDto = new ErroDto(errorMessage);
        return ResponseEntity.badRequest().body(errorDto);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity not(ValidationException e) {
        return ResponseEntity.ok().body(new ErroDto("dados não podem ser nulo"));
    }


        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity tratarErro404() {
            return ResponseEntity.notFound().build();
        }


}
