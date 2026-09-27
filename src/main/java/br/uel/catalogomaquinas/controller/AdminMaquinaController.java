package br.uel.catalogomaquinas.controller;

import br.uel.catalogomaquinas.model.Categoria;
import br.uel.catalogomaquinas.model.Maquina;
import br.uel.catalogomaquinas.service.MaquinaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/maquinas")
public class AdminMaquinaController {
    private final MaquinaService maquinaService;

    public AdminMaquinaController(MaquinaService maquinaService) {
        this.maquinaService = maquinaService;
    }

    @GetMapping
    public String listarAdmin(@RequestParam(value = "ordenarPor", required = false, defaultValue = "id") String ordenarPor,
                              @RequestParam(value = "direcao", required = false, defaultValue = "asc") String direcao,
                              Model model) {
        List<Maquina> maquinas = maquinaService.listarTodasComOrdenacao(ordenarPor, direcao);
        model.addAttribute("maquinas", maquinas);
        return "admin/listagem";
    }

    @GetMapping("/novo")
    public String novoFormulario(Model model) {
        model.addAttribute("maquina", new Maquina());
        model.addAttribute("categorias", Categoria.values());
        return "admin/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("maquina") Maquina maquina, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", Categoria.values());
            return "admin/formulario";
        }

        maquinaService.salvar(maquina);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Máquina salva com sucesso.");
        return "redirect:/admin/maquinas";
    }

    @GetMapping("/editar/{id}")
    public String editarFormulario(@PathVariable("id") Long id, Model model) {
        Maquina maquina = maquinaService.buscarPorId(id);
        model.addAttribute("maquina", maquina);
        model.addAttribute("categorias", Categoria.values());
        return "admin/formulario";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        maquinaService.deletarPorId(id);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Máquina excluída com sucesso.");
        return "redirect:/admin/maquinas";
    }
}
