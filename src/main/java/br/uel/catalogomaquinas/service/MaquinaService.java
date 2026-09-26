package br.uel.catalogomaquinas.service;

import br.uel.catalogomaquinas.exception.RecursoNaoEncontradoException;
import br.uel.catalogomaquinas.model.Categoria;
import br.uel.catalogomaquinas.model.Maquina;
import br.uel.catalogomaquinas.repository.MaquinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaquinaService {
    @Autowired
    private MaquinaRepository maquinaRepository;

    public List<Maquina> listarTodas() {
        return maquinaRepository.findAll();
    }

    public List<Maquina> listarTodasComOrdenacao(String campo, String direcao) {
        if (campo == null || campo.isBlank()) {
            campo = "id";
        }
        Sort.Direction sortDirection = "desc".equalsIgnoreCase(direcao) ? Sort.Direction.DESC : Sort.Direction.ASC;
        return maquinaRepository.findAll(Sort.by(sortDirection, campo));
    }

    public Maquina buscarPorId(Long id) {
        return maquinaRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Máquina não encontrada com o ID: " + id));
    }

    public List<Maquina> buscarPorModelo(String modelo) {
        return maquinaRepository.findByModeloContainingIgnoreCase(modelo);
    }

    public List<Maquina> buscarPorCategoria(Categoria categoria) {
        return maquinaRepository.findByCategoria(categoria);
    }

    public List<Maquina> filtrar(String modelo, Categoria categoria, Boolean apenasPromocao, Boolean apenasDisponiveis) {
        List<Maquina> resultado = maquinaRepository.findAll();

        if (modelo != null && !modelo.isBlank()) {
            resultado = resultado.stream().filter(m -> m.getModelo().toLowerCase().contains(modelo.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (categoria != null) {
            resultado = resultado.stream().filter(m -> m.getCategoria() == categoria).collect(Collectors.toList());
        }

        if (Boolean.TRUE.equals(apenasPromocao)) {
            resultado = resultado.stream().filter(Maquina::isEmPromocao).collect(Collectors.toList());
        }

        if (Boolean.TRUE.equals(apenasDisponiveis)) {
            resultado = resultado.stream().filter(m -> !m.isEsgotado()).collect(Collectors.toList());
        }

        return resultado;
    }

    public Maquina salvar(Maquina maquina) {
        if (maquina.getPromocao() == null) {
            maquina.setPromocao(0);
        }

        if (maquina.getEstoque() == null) {
            maquina.setEstoque(0);
        }

        return maquinaRepository.save(maquina);
    }

    public void deletarPorId(Long id) {
        Maquina maquina = buscarPorId(id);
        maquinaRepository.delete(maquina);
    }
}
