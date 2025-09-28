package com.ribeiro.cadastro.usuario.domain.port.in;

import com.ribeiro.cadastro.usuario.adapter.in.dto.UsuarioDTORequest;
import com.ribeiro.cadastro.usuario.adapter.out.dto.UsuarioDTOResponse;

import java.util.List;

public interface UsuarioUseCase {

    UsuarioDTOResponse salvaUsuario (UsuarioDTORequest usuarioDTORequest);

    List<UsuarioDTOResponse> listaTodosUsuarios();

    UsuarioDTOResponse buscaUsuarioPorEmail(String email);

    UsuarioDTOResponse atualizaDadosDoUsuario(Long id, UsuarioDTORequest usuarioDTORequest);

    UsuarioDTOResponse atualizaEmailDoUsuario(Long id, String novoEmail);

    void deletaUsuarioPorId(Long id);

    List<UsuarioDTOResponse> buscaUsuarioPorNome(String nome);
}
