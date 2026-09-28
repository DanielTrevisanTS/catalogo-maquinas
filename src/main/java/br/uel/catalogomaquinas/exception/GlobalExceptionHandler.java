package br.uel.catalogomaquinas.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public String tratarRecursoNaoEncontrado(RecursoNaoEncontradoException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("mensagemErro", "Ocorreu um erro: " + ex.getMessage());
        return "redirect:/catalogo";
    }
}
