package com.ribeiro.cadastro.usuario.domain.port.out;

import com.ribeiro.cadastro.usuario.adapter.in.dto.UsuarioDTORequest;
import com.ribeiro.cadastro.usuario.adapter.out.dto.UsuarioDTOResponse;
import com.ribeiro.cadastro.usuario.adapter.out.mapper.UsuarioMapper;
import com.ribeiro.cadastro.usuario.application.service.UsuarioServiceImpl;
import com.ribeiro.cadastro.usuario.domain.model.Endereco;
import com.ribeiro.cadastro.usuario.domain.model.Usuario;
import com.ribeiro.cadastro.usuario.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsultaEnderecoPortUsageTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private ConsultaEnderecoPort consultaEnderecoPort;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    private UsuarioDTORequest dtoRequest;
    private Endereco endereco;
    private Usuario usuario;
    private UsuarioDTOResponse dtoResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        dtoRequest = new UsuarioDTORequest("João", "joao@email.com", 30, "11999999999", "01001-000");
        endereco = new Endereco("01001-000", "Rua A", "Bairro B", "Cidade C", "SP");
        usuario = new Usuario(1L, "João", "joao@email.com", 30, "11999999999", endereco);
        dtoResponse = new UsuarioDTOResponse(1L, "João", "joao@email.com", 30, "11999999999",
                "01001-000", "Rua A", "Bairro B", "Cidade C", "SP");
    }

    @Test
    void deveUsarConsultaEnderecoPortParaBuscarEnderecoPorCep() {
        when(usuarioRepository.findByEmail(dtoRequest.getEmail())).thenReturn(Optional.empty());
        when(consultaEnderecoPort.consultaPorCep(dtoRequest.getCep())).thenReturn(endereco);
        when(usuarioMapper.usuarioDTOParaUsuario(dtoRequest)).thenReturn(usuario);
        when(usuarioRepository.save(usuario)).thenReturn(usuario);
        when(usuarioMapper.usuarioParaUsuarioDTO(usuario)).thenReturn(dtoResponse);

        UsuarioDTOResponse response = usuarioService.salvaUsuario(dtoRequest);

        assertEquals(dtoResponse, response);
        verify(consultaEnderecoPort).consultaPorCep("01001-000");
    }
}
