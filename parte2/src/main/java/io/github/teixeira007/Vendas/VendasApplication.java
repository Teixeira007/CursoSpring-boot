package io.github.teixeira007.Vendas;

import io.github.teixeira007.Vendas.domain.entity.Cliente;
import io.github.teixeira007.Vendas.domain.entity.Pedido;
import io.github.teixeira007.Vendas.domain.entity.Produto;
import io.github.teixeira007.Vendas.domain.repositorio.RepositoryCliente;
import io.github.teixeira007.Vendas.domain.repositorio.RepositoryClientesJDBC;
import io.github.teixeira007.Vendas.domain.repositorio.RepositoryClientesJPA;
import io.github.teixeira007.Vendas.domain.repositorio.RepositoryPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

	@Bean
	public CommandLineRunner init(
			@Autowired RepositoryCliente repositoryCliente,
			@Autowired RepositoryPedido repositoryPedido
	){
		return args -> {
			Cliente cliente = new Cliente("Vinicius");
//			Cliente cliente1 = new Cliente("Joao");

			System.out.println("Salvando os clientes");
			repositoryCliente.save(cliente);
//			repositoryCliente.save(cliente1);

			Pedido p = new Pedido();
			p.setCliente(cliente);
			p.setData(LocalDate.now());
			p.setTotal(BigDecimal.valueOf(100));

			repositoryPedido.save(p);

//			Cliente Vinicius = repositoryCliente.findClienteFetchPedidos(cliente.getId());
//			System.out.println(Vinicius);
//			System.out.println(Vinicius.getPedidos());

			repositoryPedido.findByCliente(cliente).forEach(System.out::println);

//			boolean exists = repositoryCliente.existsByNome("Vinicius");
//			System.out.println("Existe um cliente com nome Vinicius? "+exists);
//
//			System.out.println("Listando os clientes");
//			List<Cliente> obterTodos = repositoryCliente.findAll();
//			obterTodos.forEach(System.out::println);

//			System.out.println("Atualizando os clientes");

//			obterTodos.forEach(c -> {
//				c.setNome(c.getNome() + " atualizado");
//				repositoryCliente.save(c);
//			});
//
//
//			System.out.println("Listando os clientes atualizados");
//			obterTodos = repositoryCliente.findAll();
//			obterTodos.forEach(System.out::println);

//			System.out.println("Buscando por nome");
//			repositoryCliente.findByNome("Vinicius atualizado").forEach(System.out::println);

			List<Cliente> result = repositoryCliente.encontraPortNome("Vinicius");
			result.forEach(System.out::println);
////
//			System.out.println("Deletando clientes");
//
//			repositoryCliente.findAll().forEach(c -> {
//				repositoryCliente.delete(c);
//			});
//
//			obterTodos = repositoryCliente.findAll();
//			if(obterTodos.isEmpty()){
//				System.out.println("Nenhum cliente cadastrado");
//			}else{
//				obterTodos.forEach(System.out::println);
//			}
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
