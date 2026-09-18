package br.uel.catalogomaquinas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.service.annotation.GetExchange;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@Entity
@Table(name = "tb_maquinas")
public class Maquina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O modelo é obrigatório")
    private String modelo;

    @NotBlank(message = "A marca é obrigatória")
    private String marca;

    @NotNull(message = "A categoria é obrigatória")
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @NotNull(message = "O ano é obrigatório")
    @Min(value = 1900, message = "Ano inválido")
    private Integer ano;

    @NotNull(message = "O preço é obrigatório")
    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
    private BigDecimal preco;

    @Min(value = 0, message = "A promoção mínima é 0%")
    @Max(value = 100, message = "A promoção máxima é 100%")
    private Integer promocao = 0;

    @NotNull(message = "O estoque é obrigatório")
    @Min(value = 0, message = "O estoque não pode ser negativo")
    private Integer estoque = 0;

    private String imagemUrl;

    @Column(columnDefinition = "TEXT")
    private String descricao;


    public boolean isEsgotado() {
        return this.estoque == null || this.estoque <= 0;
    }

    public boolean isEmPromocao() {
        return this.promocao != null && this.promocao > 0;
    }

    public BigDecimal getPrecoComDesconto() {
        if (!isEmPromocao()) {
            return this.preco;
        }
        BigDecimal desconto = this.preco.multiply(BigDecimal.valueOf(this.promocao))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        return this.preco.subtract(desconto);
    }
}
