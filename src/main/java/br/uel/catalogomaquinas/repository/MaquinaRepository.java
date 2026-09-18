package br.uel.catalogomaquinas.repository;

import br.uel.catalogomaquinas.model.Categoria;
import br.uel.catalogomaquinas.model.Maquina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MaquinaRepository extends JpaRepository<Maquina, Long> {
    List<Maquina> findByModeloContainingIgnoreCase(String modelo);
    List<Maquina> findByCategoria(Categoria categoria);
    List<Maquina> findByEstoqueEquals(Integer estoque);
    List<Maquina> findByPromocaoGreaterThan(Integer valor);
}
