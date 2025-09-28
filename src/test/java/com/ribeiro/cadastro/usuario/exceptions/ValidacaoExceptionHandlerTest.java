package com.ribeiro.cadastro.usuario.exceptions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidacaoExceptionHandlerTest {

    private final ValidacaoExceptionHandler handler = new ValidacaoExceptionHandler();

    @Test
    void testHandleValidationExceptions() {
        // Mock dos erros de campo
        FieldError fieldError = new FieldError("obj", "nome", "Nome é obrigatório");
        List<FieldError> fieldErrors = Collections.singletonList(fieldError);

        // Mock do BindingResult
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        Mockito.when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);

        // Mock da exceção
        MethodArgumentNotValidException exception = Mockito.mock(MethodArgumentNotValidException.class);
        Mockito.when(exception.getBindingResult()).thenReturn(bindingResult);

        // Executa o handler
        ResponseEntity<ErroValidacaoResponse> response = handler.handleValidationExceptions(exception);

        // Verificações
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Erro de validação nos campos.", response.getBody().getError());
        assertTrue(response.getBody().getErrors().containsKey("nome"));
        assertEquals("Nome é obrigatório", response.getBody().getErrors().get("nome"));
    }

    @Test
    void testHandleEmailDuplicado() {
        String mensagem = "Email já está em uso";
        EmailJaCadastradoException exception = new EmailJaCadastradoException(mensagem);

        ResponseEntity<ErroValidacaoResponse> response = handler.handleEmailDuplicado(exception);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Email duplicado", response.getBody().getError());
        assertTrue(response.getBody().getErrors().containsKey("email"));
        assertEquals(mensagem, response.getBody().getErrors().get("email"));
    }

    @Test
    void testHandleUsuarioNaoEncontrado() {
        String mensagem = "Usuário com ID 123 não encontrado";
        UsuarioNaoEncontradoException exception = new UsuarioNaoEncontradoException(mensagem);

        ResponseEntity<ErroValidacaoResponse> response = handler.handleUsuarioNaoEncontrado(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Usuário não encontrado", response.getBody().getError());
        assertTrue(response.getBody().getErrors().containsKey("usuario"));
        assertEquals(mensagem, response.getBody().getErrors().get("usuario"));
    }
}
