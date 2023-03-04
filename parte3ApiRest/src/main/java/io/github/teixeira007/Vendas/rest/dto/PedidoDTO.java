package io.github.teixeira007.Vendas.rest.dto;

import io.github.teixeira007.Vendas.validation.NotEmptyList;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    @NotNull(message = "Informe o código do Cliente.")
    private Integer cliente;
    @NotNull(message = "Campo total é obrigatório.")
    private BigDecimal total;
    @NotEmptyList(message = "O pedido não pode ser realizado sem itens.")
    private List<ItemPedidoDTO> items;
}
