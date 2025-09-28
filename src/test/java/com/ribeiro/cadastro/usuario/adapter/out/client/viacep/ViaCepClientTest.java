package com.ribeiro.cadastro.usuario.adapter.out.client.viacep;

import com.ribeiro.cadastro.usuario.domain.model.Endereco;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ViaCepClientTest {

    @Mock
    private ViaCepClient viaCepClient;

    @InjectMocks
    private ConsultaEnderecoAdapter consultaEnderecoAdapter;

    private EnderecoViaCepDTOResponse mockResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockResponse = new EnderecoViaCepDTOResponse();
        mockResponse.setCep("01001-000");
        mockResponse.setLogradouro("Praça da Sé");
        mockResponse.setBairro("Sé");
        mockResponse.setLocalidade("São Paulo");
        mockResponse.setUf("SP");
    }

    @Test
    void deveChamarViaCepClientComSucesso() {
        String cep = "01001-000";
        when(viaCepClient.consultaCep(cep)).thenReturn(mockResponse);

        Endereco endereco = consultaEnderecoAdapter.consultaPorCep(cep);

        assertEquals("01001-000", endereco.getCep());
        assertEquals("Praça da Sé", endereco.getLogradouro());
        assertEquals("Sé", endereco.getBairro());
        assertEquals("São Paulo", endereco.getCidade());
        assertEquals("SP", endereco.getUf());

        verify(viaCepClient).consultaCep(cep);
    }
}
