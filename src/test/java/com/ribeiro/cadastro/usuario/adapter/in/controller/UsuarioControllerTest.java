package com.ribeiro.cadastro.usuario.adapter.in.controller;

import com.ribeiro.cadastro.usuario.adapter.in.dto.UsuarioDTORequest;
import com.ribeiro.cadastro.usuario.adapter.out.dto.UsuarioDTOResponse;
import com.ribeiro.cadastro.usuario.domain.port.in.UsuarioUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioControllerTest {

    @Mock
    private UsuarioUseCase usuarioUseCase;

    @InjectMocks
    private UsuarioController usuarioController;

    private UsuarioDTORequest usuarioDTORequest;
    private UsuarioDTOResponse usuarioDTOResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioDTORequest = new UsuarioDTORequest(); // configure os campos conforme necessário
        usuarioDTOResponse = new UsuarioDTOResponse(); // configure os campos conforme necessário
    }

    @Test
    void testSalvaUsuario() {
        when(usuarioUseCase.salvaUsuario(usuarioDTORequest)).thenReturn(usuarioDTOResponse);

        ResponseEntity<UsuarioDTOResponse> response = usuarioController.salvaUsuario(usuarioDTORequest);

        assertEquals(ResponseEntity.ok(usuarioDTOResponse), response);
        verify(usuarioUseCase).salvaUsuario(usuarioDTORequest);
    }

    @Test
    void testListaTodosUsuarios() {
        List<UsuarioDTOResponse> lista = List.of(usuarioDTOResponse);
        when(usuarioUseCase.listaTodosUsuarios()).thenReturn(lista);

        ResponseEntity<List<UsuarioDTOResponse>> response = usuarioController.listaTodosUsuarios();

        assertEquals(ResponseEntity.ok(lista), response);
        verify(usuarioUseCase).listaTodosUsuarios();
    }

    @Test
    void testBuscaUsuarioPorEmail() {
        String email = "teste@email.com";
        when(usuarioUseCase.buscaUsuarioPorEmail(email)).thenReturn(usuarioDTOResponse);

        ResponseEntity<UsuarioDTOResponse> response = usuarioController.buscaUsuarioPorEmail(email);

        assertEquals(ResponseEntity.ok(usuarioDTOResponse), response);
        verify(usuarioUseCase).buscaUsuarioPorEmail(email);
    }

    @Test
    void testAtualizaDadosDoUsuario() {
        Long id = 1L;
        when(usuarioUseCase.atualizaDadosDoUsuario(id, usuarioDTORequest)).thenReturn(usuarioDTOResponse);

        ResponseEntity<UsuarioDTOResponse> response = usuarioController.atualizaDadosDoUsuario(id, usuarioDTORequest);

        assertEquals(ResponseEntity.ok(usuarioDTOResponse), response);
        verify(usuarioUseCase).atualizaDadosDoUsuario(id, usuarioDTORequest);
    }

    @Test
    void testAtualizaEmailDoUsuario() {
        Long id = 1L;
        String novoEmail = "novo@email.com";
        when(usuarioUseCase.atualizaEmailDoUsuario(id, novoEmail)).thenReturn(usuarioDTOResponse);

        ResponseEntity<UsuarioDTOResponse> response = usuarioController.atualizaEmailDoUsuario(id, novoEmail);

        assertEquals(ResponseEntity.ok(usuarioDTOResponse), response);
        verify(usuarioUseCase).atualizaEmailDoUsuario(id, novoEmail);
    }

    @Test
    void testDeletaUsuarioPorId() {
        Long id = 1L;

        ResponseEntity<Void> response = usuarioController.deletaUsuarioPorId(id);
    }

    @Test
    void deveBuscarUsuarioPorNomeComTodosCamposPreenchidos() {

        String nome = "Davi";
        UsuarioDTOResponse usuario1 = new UsuarioDTOResponse();
        usuario1.setId(1L);
        usuario1.setNome("Davi");
        usuario1.setEmail("davi@email.com");
        usuario1.setIdade(30);
        usuario1.setTelefone("11999999999");
        usuario1.setCep("01001-000");
        usuario1.setLogradouro("Rua das Flores");
        usuario1.setBairro("Centro");
        usuario1.setCidade("São Paulo");
        usuario1.setUf("SP");

        UsuarioDTOResponse usuario2 = new UsuarioDTOResponse();
        usuario2.setId(2L);
        usuario2.setNome("Davi Silva");
        usuario2.setEmail("davi.silva@email.com");
        usuario2.setIdade(28);
        usuario2.setTelefone("11988888888");
        usuario2.setCep("02002-000");
        usuario2.setLogradouro("Av. Brasil");
        usuario2.setBairro("Jardins");
        usuario2.setCidade("São Paulo");
        usuario2.setUf("SP");

        List<UsuarioDTOResponse> mockResponse = Arrays.asList(usuario1, usuario2);
        when(usuarioUseCase.buscaUsuarioPorNome(nome)).thenReturn(mockResponse);


        ResponseEntity<List<UsuarioDTOResponse>> response = usuarioController.buscaUsuarioPorNome(nome);


        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockResponse, response.getBody());
        verify(usuarioUseCase, times(1)).buscaUsuarioPorNome(nome);
    }



}

