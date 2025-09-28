package com.ribeiro.cadastro.usuario.adapter.out.mapper;

import com.ribeiro.cadastro.usuario.adapter.in.dto.UsuarioDTORequest;
import com.ribeiro.cadastro.usuario.adapter.out.dto.UsuarioDTOResponse;
import com.ribeiro.cadastro.usuario.domain.model.Endereco;
import com.ribeiro.cadastro.usuario.domain.model.Usuario;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioMapperTest {

    private final UsuarioMapper mapper = Mappers.getMapper(UsuarioMapper.class);

    @Test
    void deveMapearUsuarioParaUsuarioDTOResponse() {
        Endereco endereco = new Endereco("01001-000", "Praça da Sé", "Sé", "São Paulo", "SP");
        Usuario usuario = new Usuario(1L, "João", "joao@email.com", 30, "11999999999", endereco);

        UsuarioDTOResponse dto = mapper.usuarioParaUsuarioDTO(usuario);

        assertEquals("João", dto.getNome());
        assertEquals("joao@email.com", dto.getEmail());
        assertEquals(30, dto.getIdade());
        assertEquals("11999999999", dto.getTelefone());
        assertEquals("01001-000", dto.getCep());
        assertEquals("Praça da Sé", dto.getLogradouro());
        assertEquals("Sé", dto.getBairro());
        assertEquals("São Paulo", dto.getCidade());
        assertEquals("SP", dto.getUf());
    }

    @Test
    void deveMapearListaUsuarioParaListaUsuarioDTOResponse() {
        Endereco endereco = new Endereco("01001-000", "Praça da Sé", "Sé", "São Paulo", "SP");
        Usuario usuario = new Usuario(1L, "João", "joao@email.com", 30, "11999999999", endereco);

        List<UsuarioDTOResponse> lista = mapper.listaUsuarioParaListaUsuarioDTO(List.of(usuario));

        assertEquals(1, lista.size());
        assertEquals("João", lista.get(0).getNome());
    }

    @Test
    void deveMapearUsuarioDTORequestParaUsuario() {
        UsuarioDTORequest dto = new UsuarioDTORequest("João", "joao@email.com", 30, "11999999999", "01001-000");

        Usuario usuario = mapper.usuarioDTOParaUsuario(dto);

        assertEquals("João", usuario.getNome());
        assertEquals("joao@email.com", usuario.getEmail());
        assertEquals(30, usuario.getIdade());
        assertEquals("11999999999", usuario.getTelefone());
        assertNotNull(usuario.getEndereco());
        assertEquals("01001-000", usuario.getEndereco().getCep());
    }

    @Test
    void deveMapearListaUsuarioDTORequestParaListaUsuario() {
        UsuarioDTORequest dto = new UsuarioDTORequest("João", "joao@email.com", 30, "11999999999", "01001-000");

        List<Usuario> lista = mapper.listaUsuarioDTOParaListaUsuario(List.of(dto));

        assertEquals(1, lista.size());
        assertEquals("João", lista.get(0).getNome());
    }
}
