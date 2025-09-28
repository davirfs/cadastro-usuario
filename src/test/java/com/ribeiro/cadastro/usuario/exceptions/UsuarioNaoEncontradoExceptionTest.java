package com.ribeiro.cadastro.usuario.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioNaoEncontradoExceptionTest {

    @Test
    void deveCriarExcecaoComMensagemCorreta() {
        String mensagem = "Usuário com ID 99 não encontrado";
        UsuarioNaoEncontradoException exception = new UsuarioNaoEncontradoException(mensagem);

        assertEquals(mensagem, exception.getMessage());
    }

    @Test
    void deveSerInstanciaDeRuntimeException() {
        UsuarioNaoEncontradoException exception = new UsuarioNaoEncontradoException("Erro");

        assertTrue(exception instanceof RuntimeException);
    }
}
