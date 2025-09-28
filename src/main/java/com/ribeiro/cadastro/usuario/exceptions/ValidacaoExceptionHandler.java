package com.ribeiro.cadastro.usuario.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidacaoExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroValidacaoResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage())
        );

        ErroValidacaoResponse response = new ErroValidacaoResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setError("Erro de validação nos campos.");
        response.setErrors(fieldErrors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<ErroValidacaoResponse> handleEmailDuplicado(EmailJaCadastradoException ex) {
        Map<String, String> erros = new HashMap<>();
        erros.put("email", ex.getMessage());

        ErroValidacaoResponse response = new ErroValidacaoResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.CONFLICT.value());
        response.setError("Email duplicado");
        response.setErrors(erros);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ErroValidacaoResponse> handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex){
        Map<String, String> erros = new HashMap<>();
        erros.put("usuario", ex.getMessage());

        ErroValidacaoResponse response = new ErroValidacaoResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setError("Usuário não encontrado");
        response.setErrors(erros);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}