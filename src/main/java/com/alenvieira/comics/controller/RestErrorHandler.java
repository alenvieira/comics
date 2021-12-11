package com.alenvieira.comics.controller;

import com.alenvieira.comics.controller.dto.FieldErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<FieldErrorDTO>> processValidationError(MethodArgumentNotValidException exception) {
        List<FieldErrorDTO> errors = new ArrayList<FieldErrorDTO>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(new FieldErrorDTO(error.getField(), error.getDefaultMessage()));
        });
        return ResponseEntity.unprocessableEntity().body(errors);
    }

}
