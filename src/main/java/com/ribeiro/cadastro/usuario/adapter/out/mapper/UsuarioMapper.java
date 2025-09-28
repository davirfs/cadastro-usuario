package com.ribeiro.cadastro.usuario.adapter.out.mapper;

import com.ribeiro.cadastro.usuario.adapter.in.dto.UsuarioDTORequest;
import com.ribeiro.cadastro.usuario.adapter.out.dto.UsuarioDTOResponse;
import com.ribeiro.cadastro.usuario.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "cep", source = "endereco.cep")
    @Mapping(target = "logradouro", source = "endereco.logradouro")
    @Mapping(target = "bairro", source = "endereco.bairro")
    @Mapping(target = "cidade", source = "endereco.cidade")
    @Mapping(target = "uf", source = "endereco.uf")
    UsuarioDTOResponse usuarioParaUsuarioDTO(Usuario usuario);

    List<UsuarioDTOResponse> listaUsuarioParaListaUsuarioDTO(List<Usuario> usuario);

    @Mapping(target = "endereco.cep", source = "cep")
    Usuario usuarioDTOParaUsuario(UsuarioDTORequest usuarioDTORequest);

    @Mapping(target = "endereco.cep", source = "cep")
    List<Usuario> listaUsuarioDTOParaListaUsuario(List<UsuarioDTORequest> usuarioDTO);

}
