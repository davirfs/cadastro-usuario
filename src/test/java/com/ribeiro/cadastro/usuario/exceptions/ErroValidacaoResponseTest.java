package com.ribeiro.cadastro.usuario.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Testes da Classe de Resposta de Erro")
class ErroValidacaoResponseTest {

    private ErroValidacaoResponse erroResponse;
    private final LocalDateTime timestamp = LocalDateTime.of(2025, 8, 1, 21, 15, 54);
    private final int status = 400;
    private final String error = "Bad Request";
    private final Map<String, String> errors = new HashMap<>();

    @BeforeEach
    void setUp() {
        erroResponse = new ErroValidacaoResponse();
        errors.put("nome", "O nome não pode ser nulo");
    }

    @Test
    @DisplayName("Deve definir e obter os valores dos campos corretamente")
    void deveTestarGettersESetters() {
        // Ação
        erroResponse.setTimestamp(timestamp);
        erroResponse.setStatus(status);
        erroResponse.setError(error);
        erroResponse.setErrors(errors);

        // Verificação
        assertThat(erroResponse.getTimestamp()).isEqualTo(timestamp);
        assertThat(erroResponse.getStatus()).isEqualTo(status);
        assertThat(erroResponse.getError()).isEqualTo(error);
        assertThat(erroResponse.getErrors()).isEqualTo(errors);
        assertThat(erroResponse.getErrors().get("nome")).isEqualTo("O nome não pode ser nulo");
    }

    @Test
    @DisplayName("Deve serializar o timestamp para JSON no formato dd-MM-yyyy HH:mm:ss")
    void deveSerializarTimestampNoFormatoCorreto() throws JsonProcessingException {
        // Cenário
        erroResponse.setTimestamp(timestamp);

        // Criamos um ObjectMapper, a ferramenta que o Spring usa para converter objetos para JSON
        ObjectMapper objectMapper = new ObjectMapper();
        // Adicionamos o módulo para ele entender como lidar com tipos do Java 8 (LocalDateTime)
        objectMapper.registerModule(new JavaTimeModule());

        // Ação
        String jsonResult = objectMapper.writeValueAsString(erroResponse);

        // Verificação
        // O formato esperado dentro do JSON será "chave":"valor"
        String timestampEsperadoNoJson = "\"timestamp\":\"01-08-2025 21:15:54\"";

        System.out.println("JSON Gerado: " + jsonResult); // Linha para depuração

        assertThat(jsonResult).contains(timestampEsperadoNoJson);
    }
}