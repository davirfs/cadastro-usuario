package com.ribeiro.cadastro.usuario.adapter.out.client.viacep;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnderecoViaCepDTOResponseTest {

    @Test
    void deveSetarEObterCamposCorretamente() {
        EnderecoViaCepDTOResponse dto = new EnderecoViaCepDTOResponse();
        dto.setCep("01001-000");
        dto.setLogradouro("Praça da Sé");
        dto.setBairro("Sé");
        dto.setLocalidade("São Paulo");
        dto.setUf("SP");

        assertEquals("01001-000", dto.getCep());
        assertEquals("Praça da Sé", dto.getLogradouro());
        assertEquals("Sé", dto.getBairro());
        assertEquals("São Paulo", dto.getLocalidade());
        assertEquals("SP", dto.getUf());
    }

    @Test
    void deveCompararObjetosComEquals() {
        EnderecoViaCepDTOResponse dto1 = new EnderecoViaCepDTOResponse();
        dto1.setCep("01001-000");
        dto1.setLogradouro("Praça da Sé");
        dto1.setBairro("Sé");
        dto1.setLocalidade("São Paulo");
        dto1.setUf("SP");

        EnderecoViaCepDTOResponse dto2 = new EnderecoViaCepDTOResponse();
        dto2.setCep("01001-000");
        dto2.setLogradouro("Praça da Sé");
        dto2.setBairro("Sé");
        dto2.setLocalidade("São Paulo");
        dto2.setUf("SP");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void deveGerarToStringComTodosCampos() {
        EnderecoViaCepDTOResponse dto = new EnderecoViaCepDTOResponse();
        dto.setCep("01001-000");
        dto.setLogradouro("Praça da Sé");
        dto.setBairro("Sé");
        dto.setLocalidade("São Paulo");
        dto.setUf("SP");

        String toString = dto.toString();
        assertTrue(toString.contains("01001-000"));
        assertTrue(toString.contains("Praça da Sé"));
        assertTrue(toString.contains("Sé"));
        assertTrue(toString.contains("São Paulo"));
        assertTrue(toString.contains("SP"));
    }
}
