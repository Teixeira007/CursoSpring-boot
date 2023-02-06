package io.github.teixeira007.Vendas.domain.repositorio;

import io.github.teixeira007.Vendas.domain.entity.Cliente;
import io.github.teixeira007.Vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryPedido extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);
}
