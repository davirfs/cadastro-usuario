package com.ribeiro.cadastro.usuario.domain.port.out;

import com.ribeiro.cadastro.usuario.domain.model.Endereco;

public interface ConsultaEnderecoPort{

    Endereco consultaPorCep(String cep);
}
