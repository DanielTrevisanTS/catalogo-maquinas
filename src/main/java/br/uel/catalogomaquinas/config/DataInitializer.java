package br.uel.catalogomaquinas.config;

import br.uel.catalogomaquinas.model.Categoria;
import br.uel.catalogomaquinas.model.Maquina;
import br.uel.catalogomaquinas.model.Usuario;
import br.uel.catalogomaquinas.repository.MaquinaRepository;
import br.uel.catalogomaquinas.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final MaquinaRepository maquinaRepository;

    public DataInitializer(UsuarioRepository usuarioRepository, MaquinaRepository maquinaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.maquinaRepository = maquinaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 1. Criação do Usuário ADMIN Padrão
        if (usuarioRepository.findByEmail("admin@admin.com").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setEmail("admin@admin.com");
            admin.setSenha("123456");
            admin.setPerfil("ADMIN");
            usuarioRepository.save(admin);
            System.out.println("|| Usuário ADMIN padrão criado com sucesso.");
        }

        // 2. Carga Inicial de Máquinas para Testes no Thymeleaf
        if (maquinaRepository.count() == 0) {
            Maquina m1 = new Maquina();
            m1.setMarca("Caterpillar");
            m1.setModelo("Escavadeira CAT 320");
            m1.setCategoria(Categoria.ESCAVADEIRA);
            m1.setAno(2022);
            m1.setPreco(new BigDecimal("680000.00"));
            m1.setPromocao(10);
            m1.setEstoque(4);
            m1.setImagemUrl("/images/EscavadeiraCAT320.jpg");
            m1.setDescricao("Escavadeira de esteira com caçamba de 1.2m³, baixo consumo de combustível e tecnologia de nivelamento.");

            Maquina m2 = new Maquina();
            m2.setMarca("JCB");
            m2.setModelo("Retroescavadeira 3CX");
            m2.setCategoria(Categoria.RETROESCAVADEIRA);
            m2.setAno(2023);
            m2.setPreco(new BigDecimal("390000.00"));
            m2.setPromocao(5);
            m2.setEstoque(8);
            m2.setImagemUrl("/images/Retroescavadeira3CX.jpg");
            m2.setDescricao("Tração 4x4, cabine fechada com ar-condicionado, motor turbo diesel. Ideal para infraestrutura urbana.");

            Maquina m3 = new Maquina();
            m3.setMarca("Komatsu");
            m3.setModelo("Pá Carregadeira WA380-6");
            m3.setCategoria(Categoria.PA_CARREGADEIRA);
            m3.setAno(2021);
            m3.setPreco(new BigDecimal("520000.00"));
            m3.setPromocao(0);
            m3.setEstoque(2);
            m3.setImagemUrl("/images/PaCarregadeiraWA380.jpg");
            m3.setDescricao("Caçamba reforçada para mineração e movimentação de agregados. Transmissão automática.");

            Maquina m4 = new Maquina();
            m4.setMarca("Liebherr");
            m4.setModelo("Guindaste LTM 1120-4.1");
            m4.setCategoria(Categoria.GUINDASTE);
            m4.setAno(2020);
            m4.setPreco(new BigDecimal("1850000.00"));
            m4.setPromocao(15);
            m4.setEstoque(1);
            m4.setImagemUrl("/images/GuindasteLTM1120-4.1.jpg");
            m4.setDescricao("Guindaste móvel de 4 eixos com capacidade de 120 toneladas e lança de 66 metros.");

            Maquina m5 = new Maquina();
            m5.setMarca("Dynapac");
            m5.setModelo("Rolo Compactador CA2500D");
            m5.setCategoria(Categoria.ROLO_COMPACTADOR);
            m5.setAno(2019);
            m5.setPreco(new BigDecimal("280000.00"));
            m5.setPromocao(0);
            m5.setEstoque(0); // Esgotado
            m5.setImagemUrl("/images/RoloCompactadorCA2500D.jpg");
            m5.setDescricao("Compactador de solo para terraplenagem e pavimentação. Alta amplitude de vibração.");

            Maquina m6 = new Maquina();
            m6.setMarca("Case CE");
            m6.setModelo("Miniescavadeira CX35D");
            m6.setCategoria(Categoria.ESCAVADEIRA);
            m6.setAno(2023);
            m6.setPreco(new BigDecimal("300000.00"));
            m6.setPromocao(0);
            m6.setEstoque(5); // Esgotado
            m6.setImagemUrl("/images/MiniEscavadeiraCX35D.jpg");
            m6.setDescricao("Miniescavadeira compacta ideal para obras urbanas e espaços reduzidos, com alta eficiência hidráulica e baixo consumo.");

            Maquina m7 = new Maquina();
            m7.setMarca("New Holland");
            m7.setModelo("Pá Carregadeira W130B");
            m7.setCategoria(Categoria.PA_CARREGADEIRA);
            m7.setAno(2022);
            m7.setPreco(new BigDecimal("450000.00"));
            m7.setPromocao(8);
            m7.setEstoque(3);
            m7.setImagemUrl("/images/PaCarregadeiraW130B.jpg");
            m7.setDescricao("Motor eletrônico de alta eficiência, excelente capacidade de carga e elevado conforto operacional para movimentação de materiais.");

            Maquina m8 = new Maquina();
            m8.setMarca("Caterpillar");
            m8.setModelo("Rolo Compactador CS54B");
            m8.setCategoria(Categoria.ROLO_COMPACTADOR);
            m8.setAno(2024);
            m8.setPreco(new BigDecimal("350000.00"));
            m8.setPromocao(0);
            m8.setEstoque(2);
            m8.setImagemUrl("/images/RoloCompactadorCS54B.jpg");
            m8.setDescricao("Alta força de compactação, sistema de vibração de duas amplitudes e visibilidade superior para o operador.");

            Maquina m9 = new Maquina();
            m9.setMarca("John Deere");
            m9.setModelo("Retroescavadeira 310L");
            m9.setCategoria(Categoria.RETROESCAVADEIRA);
            m9.setAno(2023);
            m9.setPreco(new BigDecimal("410000.00"));
            m9.setPromocao(15);
            m9.setEstoque(4);
            m9.setImagemUrl("/images/Retroescavadeira310L.jpg");
            m9.setDescricao("Sistema de tração mecânica dianteira (MFWD), estrutura reforçada e alta produtividade em escavações e trincheiras.");

            maquinaRepository.saveAll(List.of(m1, m2, m3, m4, m5, m6, m7, m8, m9));
            System.out.println("|| Carga inicial de máquinas realizada com sucesso!");
        }
    }
}
