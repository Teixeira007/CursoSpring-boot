package io.github.teixeira007.Vendas.domain.repositorio;

import io.github.teixeira007.Vendas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryProduto extends JpaRepository<Produto, Integer> {
}
