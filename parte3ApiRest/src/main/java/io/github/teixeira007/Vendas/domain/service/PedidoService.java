package io.github.teixeira007.Vendas.domain.service;

import io.github.teixeira007.Vendas.domain.entity.Pedido;
import io.github.teixeira007.Vendas.domain.enums.StatusPedido;
import io.github.teixeira007.Vendas.rest.dto.AtualizarStatusPedidoDTO;
import io.github.teixeira007.Vendas.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizarStatus(Integer id, StatusPedido status);
}
