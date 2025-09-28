package com.ribeiro.cadastro.usuario.adapter.out.client.viacep;

import lombok.Data;

@Data
public class EnderecoViaCepDTOResponse {

    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}
