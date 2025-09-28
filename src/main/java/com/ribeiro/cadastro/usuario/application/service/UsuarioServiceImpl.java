package com.ribeiro.cadastro.usuario.application.service;

import com.ribeiro.cadastro.usuario.adapter.in.dto.UsuarioDTORequest;
import com.ribeiro.cadastro.usuario.adapter.out.dto.UsuarioDTOResponse;
import com.ribeiro.cadastro.usuario.adapter.out.mapper.UsuarioMapper;
import com.ribeiro.cadastro.usuario.domain.model.Endereco;
import com.ribeiro.cadastro.usuario.domain.model.Usuario;
import com.ribeiro.cadastro.usuario.domain.port.in.UsuarioUseCase;
import com.ribeiro.cadastro.usuario.domain.port.out.ConsultaEnderecoPort;
import com.ribeiro.cadastro.usuario.domain.repository.UsuarioRepository;
import com.ribeiro.cadastro.usuario.exceptions.EmailJaCadastradoException;
import com.ribeiro.cadastro.usuario.exceptions.UsuarioNaoEncontradoException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioServiceImpl implements UsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final ConsultaEnderecoPort consultaEnderecoPort;

    public UsuarioDTOResponse salvaUsuario(UsuarioDTORequest usuarioDTORequest){

        log.info("Iniciando criação de usuário com o email : {}", usuarioDTORequest.getEmail());

        boolean emailExiste = usuarioRepository.findByEmail(usuarioDTORequest.getEmail()).isPresent();

        if (emailExiste){
            log.warn("Tentativa de cadastro com email já existente: {}", usuarioDTORequest.getEmail());
            throw new EmailJaCadastradoException("O email já está cadastrado");
        }

        Endereco endereco = consultaEnderecoPort.consultaPorCep(usuarioDTORequest.getCep());

        Usuario usuario = usuarioMapper.usuarioDTOParaUsuario(usuarioDTORequest);
        usuario.setEndereco(endereco);
        Usuario salvo = usuarioRepository.save(usuario);

        log.info("Usuário salvo com sucesso. ID: {}, Email: {}", salvo.getId(), salvo.getEmail());

        return usuarioMapper.usuarioParaUsuarioDTO(usuario);
    }

    public List<UsuarioDTOResponse> listaTodosUsuarios(){
        log.info("Buscando todos os usuários cadastrados");
        List<Usuario> usuarios = usuarioRepository.findAll();
        log.info("Total de usuários encontrados: {}", usuarios.size());
        return usuarioMapper.listaUsuarioParaListaUsuarioDTO(usuarios);
    }

    public void deletaUsuarioPorId(Long id){
        log.info("Tentando deletar usuário com Id: {}", id);
        usuarioRepository.findById(id)
                .orElseThrow(
                        () -> { log.error("Usuário com id {} não encontrado", id);
                            return new UsuarioNaoEncontradoException("Usuário com id " + id + " não encontrado");
                        });
        usuarioRepository.deleteById(id);
        log.info("Usuário com ID {} deletado com sucesso", id);
    }

    public UsuarioDTOResponse atualizaDadosDoUsuario(Long id, UsuarioDTORequest novoUsuario) {
        log.info("Atualizando dados do usuário com id {}", id);
        Usuario usuario =
               usuarioRepository.findById(id)
                       .orElseThrow(
                               () -> { log.error("Usuário com id {} não encontrado para atualizar", id);
                                   return new UsuarioNaoEncontradoException("Usuário com id " + id + " não encontrado");
                               });

        usuario.setNome(novoUsuario.getNome());
        usuario.setEmail(novoUsuario.getEmail());
        usuario.setIdade(novoUsuario.getIdade());
        usuario.setTelefone(novoUsuario.getTelefone());

        Usuario atualizado = usuarioRepository.save(usuario);

        log.info("Dados do usuário com id {} atualizado com sucesso", id);

        return usuarioMapper.usuarioParaUsuarioDTO(atualizado);
    }

    public UsuarioDTOResponse atualizaEmailDoUsuario(Long id, @Valid String novoEmail) {
        log.info("Atualizando email do usuário de id {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(
                        () -> { log.error("Usuário com id {} não encontrado para atualização de email", id);
                            return new UsuarioNaoEncontradoException("Usuário com id " + id + " não encontrado");
                        });
        usuario.setEmail(novoEmail);
        Usuario novoEmailSalvo = usuarioRepository.save(usuario);
        log.info("Email de usuário com id {} atualizado com sucesso", id);
        return usuarioMapper.usuarioParaUsuarioDTO(novoEmailSalvo);
    }

    public UsuarioDTOResponse buscaUsuarioPorEmail(String email) {
        log.info("Buscando informações do usuário por email: {}", email);

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> { log.error("Usuário com email {} não encontrado", email);
                    return new UsuarioNaoEncontradoException("Usuário com email " + email + " não encontrado.");
                });

        log.info("Usuário com email {} foi econtrado com sucesso", email);

        return usuarioMapper.usuarioParaUsuarioDTO(usuario);
    }

    public List<UsuarioDTOResponse> buscaUsuarioPorNome(String nome){
        log.info("Buscando usuário com nome de {}", nome);
        List<Usuario> usuario = usuarioRepository.findByNomeContainingIgnoreCase(nome);
        if (usuario.isEmpty()){
            log.error("Nenhum usuário com nome semelhante a {} foi encontrado", nome);
            throw new UsuarioNaoEncontradoException("Usuário com nome de " + nome + " não foi encontrado");
        }
        log.info("Total de usuários encontrados com nome semalhente de '{}': {}", nome, usuario.size());
        return usuarioMapper.listaUsuarioParaListaUsuarioDTO(usuario);
    }
}
