package com.ribeiro.cadastro.usuario.adapter.in.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioDTORequest {

    @NotBlank(message = "Nome não pode ser nulo ou vazio.")
    private String nome;

    @NotBlank(message = "Email não pode ser nulo ou vazio")
    private String email;

    @Min(value = 18, message = "Idade mínima tem que ser 18 anos.")
    private Integer idade;

    @NotBlank(message = "Telefone não pode ser nulo ou vazio.")
    private String telefone;

    @NotBlank(message = "CEP não pode ser nulo ou vazio.")
    private String cep;
}
