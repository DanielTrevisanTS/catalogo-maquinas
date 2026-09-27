package br.uel.catalogomaquinas.controller;

import br.uel.catalogomaquinas.model.Categoria;
import br.uel.catalogomaquinas.model.Maquina;
import br.uel.catalogomaquinas.repository.MaquinaRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maquinas")
public class MaquinaController {

    private final MaquinaRepository maquinaRepository;

    public MaquinaController(MaquinaRepository maquinaRepository) {
        this.maquinaRepository = maquinaRepository;
    }

    // 1. Listar todas as máquinas
    @GetMapping
    public List<Maquina> listarTodas() {
        return maquinaRepository.findAll();
    }

    // 2. Buscar máquina por ID
    @GetMapping("/{id}")
    public ResponseEntity<Maquina> buscarPorId(@PathVariable Long id) {
        return maquinaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Filtrar máquinas por Categoria (Enum)
    @GetMapping("/categoria/{categoria}")
    public List<Maquina> buscarPorCategoria(@PathVariable Categoria categoria) {
        return maquinaRepository.findByCategoria(categoria);
    }

    // 4. Endpoint auxiliar: Expor os valores do Enum Categoria para o Frontend
    @GetMapping("/categorias")
    public Categoria[] listarCategorias() {
        return Categoria.values();
    }

    // 5. Cadastrar nova máquina (Aplica validações de Bean Validation)
    @PostMapping
    public ResponseEntity<Maquina> cadastrar(@Valid @RequestBody Maquina maquina) {
        Maquina novaMaquina = maquinaRepository.save(maquina);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMaquina);
    }

    // 6. Atualizar máquina existente
    @PutMapping("/{id}")
    public ResponseEntity<Maquina> atualizar(@PathVariable Long id, @Valid @RequestBody Maquina maquinaAtualizada) {
        return maquinaRepository.findById(id)
                .map(maquinaExistente -> {
                    maquinaAtualizada.setId(maquinaExistente.getId());
                    Maquina maquinaSalva = maquinaRepository.save(maquinaAtualizada);
                    return ResponseEntity.ok(maquinaSalva);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 7. Deletar máquina por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!maquinaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        maquinaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}