package io.github.teixeira007.Vendas.rest.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesItemPedidoDTO {
    private String descricaoProduto;
    private BigDecimal precoUnitario;
    private Integer quantidade;
}
