package br.uel.catalogomaquinas.config;

import br.uel.catalogomaquinas.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AutorizacaoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado == null || !"ADMIN".equalsIgnoreCase(usuarioLogado.getPerfil())) {
            response.sendRedirect("/login?erro=acesso-negado");
            return false;
        }

        return true;
    }
}
