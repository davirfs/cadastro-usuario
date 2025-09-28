package com.ribeiro.cadastro.usuario.adapter.in.controller;

import com.ribeiro.cadastro.usuario.adapter.in.dto.UsuarioDTORequest;
import com.ribeiro.cadastro.usuario.adapter.out.dto.UsuarioDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuário", description = "Cadastro, atualização e deleção de usuário")
public interface UsuarioControllerImpl {

    @PostMapping
    @Operation(summary = "Criar usuário", description = "Cria e salva um novo usuário.")
    @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso.")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    ResponseEntity<UsuarioDTOResponse> salvaUsuario(@Valid @RequestBody UsuarioDTORequest usuarioDTO);


    @GetMapping
    @Operation(summary = "Busca lista de usuários", description = "Busca lista de todos os usuários")
    @ApiResponse(responseCode = "200", description = "Listado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    ResponseEntity<List<UsuarioDTOResponse>> listaTodosUsuarios();

    @GetMapping("/{email}")
    @Operation(summary = "Busca usuário por email", description = "Busca todos usuários por email")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@PathVariable("email") String email);


    @PutMapping("/{id}")
    @Operation(summary = "Atualiza dados do usuário", description = "Atualiza todos os dados de usuario")
    @ApiResponse(responseCode = "200", description = "Dados atualizado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    ResponseEntity<UsuarioDTOResponse> atualizaDadosDoUsuario(@PathVariable("id") Long id,
                                                              @Valid @RequestBody UsuarioDTORequest usuarioDTORequest);

    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza email", description = "Atualiza email do usuário")
    @ApiResponse(responseCode = "200", description = "Email atualizado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    ResponseEntity<UsuarioDTOResponse> atualizaEmailDoUsuario(@PathVariable("id") Long id,
                                                              @Valid String novoEmail);

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta dados do usuário", description = "Deleta dados do usuário por id")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso.")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    ResponseEntity<Void> deletaUsuarioPorId(@PathVariable("id") Long id);

    @GetMapping("/nome")
    @Operation(summary = "Busca usuário por nome", description = "Busca todos usuários por nome")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    ResponseEntity<List<UsuarioDTOResponse>> buscaUsuarioPorNome(@RequestParam("nome") String nome);
}
