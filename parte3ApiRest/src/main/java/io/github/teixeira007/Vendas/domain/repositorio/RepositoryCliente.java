package io.github.teixeira007.Vendas.domain.repositorio;

import io.github.teixeira007.Vendas.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface RepositoryCliente extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByNome(String nome);

    boolean existsByNome(String nome);

//    criando query metodos
    @Query(value = " select c from Cliente c where c.nome like :nome")
    List<Cliente>  encontraPortNome(@Param("nome") String nome);

    @Query(value = "delete from Cliente c where c.nome =:nome")
    @Modifying
    void deleteByNome(String nome);

    @Query(value = "select c from Cliente c left join fetch c.pedidos where c.id = :id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);
}
