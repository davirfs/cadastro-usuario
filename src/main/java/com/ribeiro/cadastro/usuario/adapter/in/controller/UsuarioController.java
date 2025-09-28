package com.ribeiro.cadastro.usuario.adapter.in.controller;

import com.ribeiro.cadastro.usuario.adapter.in.dto.UsuarioDTORequest;
import com.ribeiro.cadastro.usuario.adapter.out.dto.UsuarioDTOResponse;
import com.ribeiro.cadastro.usuario.domain.port.in.UsuarioUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Valid
public class UsuarioController implements UsuarioControllerImpl{

    private final UsuarioUseCase usuarioUseCase;

    @PostMapping
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@Valid @RequestBody UsuarioDTORequest usuarioDTORequest){
        return ResponseEntity.ok(usuarioUseCase.salvaUsuario(usuarioDTORequest));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTOResponse>> listaTodosUsuarios(){
        return ResponseEntity.ok(usuarioUseCase.listaTodosUsuarios());
    }

    @GetMapping("/{email}")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(usuarioUseCase.buscaUsuarioPorEmail(email));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadosDoUsuario(@PathVariable("id") Long id,
                                                                    @Valid @RequestBody UsuarioDTORequest usuarioDTORequest){
        return ResponseEntity.ok(usuarioUseCase.atualizaDadosDoUsuario(id, usuarioDTORequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioDTOResponse> atualizaEmailDoUsuario(@PathVariable("id") Long id,
                                                             @Valid @RequestBody String novoEmail){

        return ResponseEntity.ok(usuarioUseCase.atualizaEmailDoUsuario(id, novoEmail));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaUsuarioPorId(@PathVariable("id") Long id){
        usuarioUseCase.deletaUsuarioPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome")
    public ResponseEntity<List<UsuarioDTOResponse>> buscaUsuarioPorNome(@RequestParam("nome") String nome){
        return ResponseEntity.ok(usuarioUseCase.buscaUsuarioPorNome(nome));
    }
}
