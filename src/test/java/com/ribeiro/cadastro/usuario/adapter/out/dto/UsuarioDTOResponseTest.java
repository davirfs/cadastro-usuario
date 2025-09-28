package com.ribeiro.cadastro.usuario.adapter.out.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDTOResponseTest {

    @Test
    void deveSetarEObterCamposCorretamente() {
        UsuarioDTOResponse dto = new UsuarioDTOResponse();
        dto.setId(1L);
        dto.setNome("João da Silva");
        dto.setEmail("joao@email.com");
        dto.setIdade(30);
        dto.setTelefone("11999999999");
        dto.setCep("01001-000");
        dto.setLogradouro("Praça da Sé");
        dto.setBairro("Sé");
        dto.setCidade("São Paulo");
        dto.setUf("SP");

        assertEquals(1L, dto.getId());
        assertEquals("João da Silva", dto.getNome());
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
    void deveCompararObjetosComEqualsEHashCode() {
        UsuarioDTOResponse dto1 = new UsuarioDTOResponse(
                1L, "João da Silva", "joao@email.com", 30,
                "11999999999", "01001-000", "Praça da Sé",
                "Sé", "São Paulo", "SP"
        );

        UsuarioDTOResponse dto2 = new UsuarioDTOResponse(
                1L, "João da Silva", "joao@email.com", 30,
                "11999999999", "01001-000", "Praça da Sé",
                "Sé", "São Paulo", "SP"
        );

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void deveGerarToStringComTodosCampos() {
        UsuarioDTOResponse dto = new UsuarioDTOResponse(
                1L, "João da Silva", "joao@email.com", 30,
                "11999999999", "01001-000", "Praça da Sé",
                "Sé", "São Paulo", "SP"
        );

        String toString = dto.toString();
        assertTrue(toString.contains("João da Silva"));
        assertTrue(toString.contains("joao@email.com"));
        assertTrue(toString.contains("São Paulo"));
        assertTrue(toString.contains("SP"));
    }
}
