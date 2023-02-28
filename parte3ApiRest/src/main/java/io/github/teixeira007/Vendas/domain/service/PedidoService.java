package io.github.teixeira007.Vendas.domain.service;

import io.github.teixeira007.Vendas.domain.entity.Pedido;
import io.github.teixeira007.Vendas.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
}
