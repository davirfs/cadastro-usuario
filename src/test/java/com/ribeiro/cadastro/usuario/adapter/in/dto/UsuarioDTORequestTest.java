package com.ribeiro.cadastro.usuario.adapter.in.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDTORequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void devePassarComDadosValidos() {
        UsuarioDTORequest dto = new UsuarioDTORequest(
                "João da Silva",
                "joao@email.com",
                25,
                "11999999999",
                "01001-000"
        );

        Set<ConstraintViolation<UsuarioDTORequest>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty(), "Não deve haver violações para dados válidos");
    }

    @Test
    void deveFalharComNomeVazio() {
        UsuarioDTORequest dto = new UsuarioDTORequest(
                "",
                "joao@email.com",
                25,
                "11999999999",
                "01001-000"
        );

        Set<ConstraintViolation<UsuarioDTORequest>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("nome")));
    }

    @Test
    void deveFalharComEmailVazio() {
        UsuarioDTORequest dto = new UsuarioDTORequest(
                "João",
                "",
                25,
                "11999999999",
                "01001-000"
        );

        Set<ConstraintViolation<UsuarioDTORequest>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")));
    }

    @Test
    void deveFalharComIdadeMenorQue18() {
        UsuarioDTORequest dto = new UsuarioDTORequest(
                "João",
                "joao@email.com",
                17,
                "11999999999",
                "01001-000"
        );

        Set<ConstraintViolation<UsuarioDTORequest>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("idade")));
    }

    @Test
    void deveFalharComTelefoneVazio() {
        UsuarioDTORequest dto = new UsuarioDTORequest(
                "João",
                "joao@email.com",
                25,
                "",
                "01001-000"
        );

        Set<ConstraintViolation<UsuarioDTORequest>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("telefone")));
    }

    @Test
    void deveFalharComCepVazio() {
        UsuarioDTORequest dto = new UsuarioDTORequest(
                "João",
                "joao@email.com",
                25,
                "11999999999",
                ""
        );

        Set<ConstraintViolation<UsuarioDTORequest>> violations = validator.validate(dto);
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("cep")));
    }
}
