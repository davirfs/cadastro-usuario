package com.ribeiro.cadastro.usuario.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {

    @Test
    void deveSetarEObterCamposCorretamente() {
        Endereco endereco = new Endereco();
        endereco.setCep("01001-000");
        endereco.setLogradouro("Praça da Sé");
        endereco.setBairro("Sé");
        endereco.setCidade("São Paulo");
        endereco.setUf("SP");

        assertEquals("01001-000", endereco.getCep());
        assertEquals("Praça da Sé", endereco.getLogradouro());
        assertEquals("Sé", endereco.getBairro());
        assertEquals("São Paulo", endereco.getCidade());
        assertEquals("SP", endereco.getUf());
    }

    @Test
    void deveCompararObjetosComEqualsEHashCode() {
        Endereco endereco1 = new Endereco("01001-000", "Praça da Sé", "Sé", "São Paulo", "SP");
        Endereco endereco2 = new Endereco("01001-000", "Praça da Sé", "Sé", "São Paulo", "SP");

        assertEquals(endereco1, endereco2);
        assertEquals(endereco1.hashCode(), endereco2.hashCode());
    }

    @Test
    void deveGerarToStringComTodosCampos() {
        Endereco endereco = new Endereco("01001-000", "Praça da Sé", "Sé", "São Paulo", "SP");

        String toString = endereco.toString();
        assertTrue(toString.contains("01001-000"));
        assertTrue(toString.contains("Praça da Sé"));
        assertTrue(toString.contains("Sé"));
        assertTrue(toString.contains("São Paulo"));
        assertTrue(toString.contains("SP"));
    }
}
