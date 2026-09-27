package br.uel.catalogomaquinas.controller;

import br.uel.catalogomaquinas.model.Categoria;
import br.uel.catalogomaquinas.model.Maquina;
import br.uel.catalogomaquinas.service.MaquinaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ClienteCatalogoController {
    private final MaquinaService maquinaService;

    public ClienteCatalogoController(MaquinaService maquinaService) {
        this.maquinaService = maquinaService;
    }

    @GetMapping({"/", "/catalgo"})
    public String exibirCatalogo(@RequestParam(value = "modelo", required = false) String modelo,
                                 @RequestParam(value = "categoria", required = false) Categoria categoria,
                                 @RequestParam(value = "apenasPromocao", required = false) Boolean apenasPromocao,
                                 @RequestParam(value = "apenasDisponiveis", required = false) Boolean apenasDisponiveis,
                                 @RequestParam(value = "ordenarPor", required = false, defaultValue = "id") String ordenarPor,
                                 @RequestParam(value = "direcao", required = false, defaultValue = "asc") String direcao, Model model) {
        List<Maquina> maquinas;

        if ((modelo != null && !modelo.isBlank()) || categoria != null || Boolean.TRUE.equals(apenasPromocao) || Boolean.TRUE.equals(apenasDisponiveis)) {
            maquinas = maquinaService.filtrar(modelo, categoria, apenasPromocao, apenasDisponiveis);
        } else {
            maquinas = maquinaService.listarTodasComOrdenacao(ordenarPor, direcao);
        }

        model.addAttribute("maquinas", maquinas);
        model.addAttribute("categorias", Categoria.values());
        model.addAttribute("modeloBusca", modelo);
        model.addAttribute("categoriaSelecionada", categoria);
        model.addAttribute("apenasPromocao", apenasPromocao);
        model.addAttribute("apenasDisponiveis", apenasDisponiveis);

        return "cliente/catalogo";
    }

    @GetMapping("/catalogo/detalhes/{id}")
    public String exibirDetalhes(@PathVariable("id") Long id, Model model) {
        Maquina maquina = maquinaService.buscarPorId(id);
        model.addAttribute("maquina", maquina);
        return "cliente/detalhes";
    }
}
