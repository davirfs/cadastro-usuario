package com.ribeiro.cadastro.usuario.domain.repository;

import com.ribeiro.cadastro.usuario.domain.model.Endereco;
import com.ribeiro.cadastro.usuario.domain.model.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve salvar e buscar usuário por email")
    void testFindByEmail() {
        Usuario usuario = new Usuario(null, "João", "joao@email.com", 30, "11999999999",
                new Endereco("01001-000", "Rua A", "Bairro B", "São Paulo", "SP"));
        usuarioRepository.save(usuario);

        Optional<Usuario> resultado = usuarioRepository.findByEmail("joao@email.com");

        assertTrue(resultado.isPresent());
        assertEquals("João", resultado.get().getNome());
    }

    @Test
    @DisplayName("Deve buscar usuários por nome ignorando case")
    void testFindByNomeContainingIgnoreCase() {
        Usuario usuario1 = new Usuario(null, "Maria Silva", "maria@email.com", 28, "11988888888",
                new Endereco("01002-000", "Rua B", "Bairro C", "São Paulo", "SP"));
        Usuario usuario2 = new Usuario(null, "Mariana Souza", "mariana@email.com", 32, "11977777777",
                new Endereco("01003-000", "Rua C", "Bairro D", "São Paulo", "SP"));

        usuarioRepository.saveAll(List.of(usuario1, usuario2));

        List<Usuario> resultados = usuarioRepository.findByNomeContainingIgnoreCase("maria");

        assertEquals(2, resultados.size());
        assertTrue(resultados.stream().anyMatch(u -> u.getNome().equals("Maria Silva")));
        assertTrue(resultados.stream().anyMatch(u -> u.getNome().equals("Mariana Souza")));
    }
}
