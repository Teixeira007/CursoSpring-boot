package io.github.teixeira007.Vendas.domain.repositorio;

import io.github.teixeira007.Vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RepositoryCliente extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByNome(String nome);

    boolean existsByNome(String nome);
}
