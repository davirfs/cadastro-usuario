package com.ribeiro.cadastro.usuario.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void deveSetarEObterCamposCorretamente() {
        Endereco endereco = new Endereco("01001-000", "Rua A", "Bairro B", "São Paulo", "SP");

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("João da Silva");
        usuario.setEmail("joao@email.com");
        usuario.setIdade(30);
        usuario.setTelefone("11999999999");
        usuario.setEndereco(endereco);

        assertEquals(1L, usuario.getId());
        assertEquals("João da Silva", usuario.getNome());
        assertEquals("joao@email.com", usuario.getEmail());
        assertEquals(30, usuario.getIdade());
        assertEquals("11999999999", usuario.getTelefone());
        assertEquals(endereco, usuario.getEndereco());
    }

    @Test
    void deveCompararObjetosComEqualsEHashCode() {
        Endereco endereco = new Endereco("01001-000", "Rua A", "Bairro B", "São Paulo", "SP");

        Usuario usuario1 = new Usuario(1L, "João", "joao@email.com", 30, "11999999999", endereco);
        Usuario usuario2 = new Usuario(1L, "João", "joao@email.com", 30, "11999999999", endereco);

        assertEquals(usuario1, usuario2);
        assertEquals(usuario1.hashCode(), usuario2.hashCode());
    }

    @Test
    void deveGerarToStringComTodosCampos() {
        Endereco endereco = new Endereco("01001-000", "Rua A", "Bairro B", "São Paulo", "SP");

        Usuario usuario = new Usuario(1L, "João", "joao@email.com", 30, "11999999999", endereco);

        String toString = usuario.toString();
        assertTrue(toString.contains("João"));
        assertTrue(toString.contains("joao@email.com"));
        assertTrue(toString.contains("São Paulo"));
        assertTrue(toString.contains("SP"));
    }
}
