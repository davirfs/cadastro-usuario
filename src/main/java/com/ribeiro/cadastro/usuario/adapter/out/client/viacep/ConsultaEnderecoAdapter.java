package com.ribeiro.cadastro.usuario.adapter.out.client.viacep;

import com.ribeiro.cadastro.usuario.domain.model.Endereco;
import com.ribeiro.cadastro.usuario.domain.port.out.ConsultaEnderecoPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsultaEnderecoAdapter implements ConsultaEnderecoPort {

    private final ViaCepClient client;

    @Override
    public Endereco consultaPorCep(String cep) {
        EnderecoViaCepDTOResponse response = client.consultaCep(cep);
        return new Endereco(response.getCep(),
                response.getLogradouro(),
                response.getBairro(),
                response.getLocalidade(),
                response.getUf()) ;
    }
}
