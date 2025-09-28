package com.ribeiro.cadastro.usuario.application.service;

import com.ribeiro.cadastro.usuario.adapter.in.dto.UsuarioDTORequest;
import com.ribeiro.cadastro.usuario.adapter.out.dto.UsuarioDTOResponse;
import com.ribeiro.cadastro.usuario.adapter.out.mapper.UsuarioMapper;
import com.ribeiro.cadastro.usuario.domain.model.Endereco;
import com.ribeiro.cadastro.usuario.domain.model.Usuario;
import com.ribeiro.cadastro.usuario.domain.port.out.ConsultaEnderecoPort;
import com.ribeiro.cadastro.usuario.domain.repository.UsuarioRepository;
import com.ribeiro.cadastro.usuario.exceptions.EmailJaCadastradoException;
import com.ribeiro.cadastro.usuario.exceptions.UsuarioNaoEncontradoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private ConsultaEnderecoPort consultaEnderecoPort;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private UsuarioDTORequest dtoRequest;
    private Usuario usuario;
    private UsuarioDTOResponse dtoResponse;
    private Endereco endereco;

    @BeforeEach
    void setUp() {
        dtoRequest = new UsuarioDTORequest("João", "joao@email.com", 30, "11999999999", "01001-000");
        endereco = new Endereco("01001-000", "Rua A", "Bairro B", "Cidade C", "SP");
        usuario = new Usuario(1L, "João", "joao@email.com", 30, "11999999999", endereco);
        dtoResponse = new UsuarioDTOResponse(1L, "João", "joao@email.com", 30, "11999999999",
                "01001-000", "Rua A", "Bairro B", "Cidade C", "SP");
    }

    @Test
    void deveSalvarUsuarioComSucesso() {
        when(usuarioRepository.findByEmail(dtoRequest.getEmail())).thenReturn(Optional.empty());
        when(consultaEnderecoPort.consultaPorCep(dtoRequest.getCep())).thenReturn(endereco);
        when(usuarioMapper.usuarioDTOParaUsuario(dtoRequest)).thenReturn(usuario);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        when(usuarioMapper.usuarioParaUsuarioDTO(usuario)).thenReturn(dtoResponse);

        UsuarioDTOResponse response = usuarioService.salvaUsuario(dtoRequest);

        assertEquals(dtoResponse, response);
        verify(usuarioRepository).save(usuario);
    }

    @Test
    void deveLancarExcecaoQuandoEmailJaExiste() {
        when(usuarioRepository.findByEmail(dtoRequest.getEmail())).thenReturn(Optional.of(usuario));

        assertThrows(EmailJaCadastradoException.class, () -> usuarioService.salvaUsuario(dtoRequest));
        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void deveListarTodosUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
        when(usuarioMapper.listaUsuarioParaListaUsuarioDTO(List.of(usuario))).thenReturn(List.of(dtoResponse));

        List<UsuarioDTOResponse> response = usuarioService.listaTodosUsuarios();

        assertEquals(1, response.size());
        assertEquals(dtoResponse, response.get(0));
    }

    @Test
    void deveDeletarUsuarioPorIdComSucesso() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        usuarioService.deletaUsuarioPorId(1L);

        verify(usuarioRepository).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoAoDeletarUsuarioInexistente() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UsuarioNaoEncontradoException.class, () -> usuarioService.deletaUsuarioPorId(1L));
        verify(usuarioRepository, never()).deleteById(any());
    }

    @Test
    void deveAtualizarDadosDoUsuario() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any())).thenReturn(usuario);
        when(usuarioMapper.usuarioParaUsuarioDTO(usuario)).thenReturn(dtoResponse);

        UsuarioDTOResponse response = usuarioService.atualizaDadosDoUsuario(1L, dtoRequest);

        assertEquals(dtoResponse, response);
    }

    @Test
    void deveAtualizarEmailDoUsuario() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any())).thenReturn(usuario);
        when(usuarioMapper.usuarioParaUsuarioDTO(usuario)).thenReturn(dtoResponse);

        UsuarioDTOResponse response = usuarioService.atualizaEmailDoUsuario(1L, "novo@email.com");

        assertEquals(dtoResponse, response);
    }

    @Test
    void deveBuscarUsuarioPorEmailComSucesso() {
        when(usuarioRepository.findByEmail("joao@email.com")).thenReturn(Optional.of(usuario));
        when(usuarioMapper.usuarioParaUsuarioDTO(usuario)).thenReturn(dtoResponse);

        UsuarioDTOResponse response = usuarioService.buscaUsuarioPorEmail("joao@email.com");

        assertEquals(dtoResponse, response);
    }

    @Test
    void deveLancarExcecaoAoBuscarUsuarioPorEmailInexistente() {
        when(usuarioRepository.findByEmail("joao@email.com")).thenReturn(Optional.empty());

        assertThrows(UsuarioNaoEncontradoException.class, () -> usuarioService.buscaUsuarioPorEmail("joao@email.com"));
    }

    @Test
    void deveBuscarUsuarioPorNomeComSucesso() {
        when(usuarioRepository.findByNomeContainingIgnoreCase("João")).thenReturn(List.of(usuario));
        when(usuarioMapper.listaUsuarioParaListaUsuarioDTO(List.of(usuario))).thenReturn(List.of(dtoResponse));

        List<UsuarioDTOResponse> response = usuarioService.buscaUsuarioPorNome("João");

        assertEquals(1, response.size());
        assertEquals(dtoResponse, response.get(0));
    }

    @Test
    void deveLancarExcecaoAoBuscarUsuarioPorNomeInexistente() {
        when(usuarioRepository.findByNomeContainingIgnoreCase("Inexistente")).thenReturn(List.of());

        assertThrows(UsuarioNaoEncontradoException.class, () -> usuarioService.buscaUsuarioPorNome("Inexistente"));
    }
}
