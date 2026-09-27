package br.uel.catalogomaquinas.controller;

import br.uel.catalogomaquinas.model.Usuario;
import br.uel.catalogomaquinas.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {
    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String exibirLogin(@RequestParam(value = "erro", required = false) String erro, Model model) {
        if ("acesso-negado".equals(erro)) {
            model.addAttribute("mensagemErro", "Acesso restrito a administradores. Faça login para continuar.");
        }
        return "login";
    }

    @PutMapping("/fazer-login")
    public String fazerLogin(@RequestParam("email") String email, @RequestParam("senha") String senha, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            Usuario usuario = usuarioService.autenticar(email, senha);
            session.setAttribute("usuarioLogado", usuario);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Login realizado com sucesso!");
            return "redirect:/admin/maquinas";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", e.getMessage());
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Sessão encerrada com sucesso");
        return "redirect:/catalogo";
    }
}

