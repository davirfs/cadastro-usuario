package com.ribeiro.cadastro.usuario.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailJaCadastradoExceptionTest {

    @Test
    void deveCriarExcecaoComMensagemCorreta() {
        // Arrange
        String mensagemEsperada = "Email já está cadastrado";

        // Act
        EmailJaCadastradoException excecao = new EmailJaCadastradoException(mensagemEsperada);

        // Assert
        assertEquals(mensagemEsperada, excecao.getMessage());
    }
}