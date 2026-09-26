package br.uel.catalogomaquinas.config;

import br.uel.catalogomaquinas.model.Usuario;
import br.uel.catalogomaquinas.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UsuarioRepository usuarioRepository;

    public DataInitializer(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.findByEmail("admin@admin.com").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setEmail("admin@admin.com");
            admin.setSenha("123456");
            admin.setPerfil("ADMIN");

            usuarioRepository.save(admin);
            System.out.println("|| Usuário ADMIN padrão criado.");
        }
    }
}
