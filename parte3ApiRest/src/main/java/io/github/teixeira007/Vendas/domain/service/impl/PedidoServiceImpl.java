package io.github.teixeira007.Vendas.domain.service.impl;

import io.github.teixeira007.Vendas.domain.repositorio.RepositoryPedido;
import io.github.teixeira007.Vendas.domain.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private RepositoryPedido repositoryPedido;

    public PedidoServiceImpl(RepositoryPedido repositoryPedido) {
        this.repositoryPedido = repositoryPedido;
    }
}
