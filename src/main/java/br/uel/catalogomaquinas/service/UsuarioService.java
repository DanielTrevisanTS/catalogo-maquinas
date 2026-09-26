package br.uel.catalogomaquinas.service;

import br.uel.catalogomaquinas.exception.RecursoNaoEncontradoException;
import br.uel.catalogomaquinas.model.Usuario;
import br.uel.catalogomaquinas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario autenticar(String email, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

        if (usuarioOpt.isEmpty()) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado com o e-mail informado.");
        }

        Usuario usuario = usuarioOpt.get();

        if (!usuario.getSenha().equals(senha)) {
            throw new IllegalArgumentException("Senha incorreta.");
        }

        return usuario;
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
