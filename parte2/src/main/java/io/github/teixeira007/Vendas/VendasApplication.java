package io.github.teixeira007.Vendas;

import io.github.teixeira007.Vendas.domain.entity.Cliente;
import io.github.teixeira007.Vendas.domain.repositorio.RepositoryCliente;
import io.github.teixeira007.Vendas.domain.repositorio.RepositoryClientesJDBC;
import io.github.teixeira007.Vendas.domain.repositorio.RepositoryClientesJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

	@Bean
	public CommandLineRunner init(@Autowired RepositoryCliente repositoryCliente){
		return args -> {
			Cliente cliente = new Cliente("Vinicius");
			Cliente cliente1 = new Cliente("Joao");

			System.out.println("Salvando os clientes");
			repositoryCliente.save(cliente);
			repositoryCliente.save(cliente1);

			boolean exists = repositoryCliente.existsByNome("Vinicius");
			System.out.println("Existe um cliente com nome Vinicius? "+exists);

			System.out.println("Listando os clientes");
			List<Cliente> obterTodos = repositoryCliente.findAll();
			obterTodos.forEach(System.out::println);

			System.out.println("Atualizando os clientes");

			obterTodos.forEach(c -> {
				c.setNome(c.getNome() + " atualizado");
				repositoryCliente.save(c);
			});


			System.out.println("Listando os clientes atualizados");
			obterTodos = repositoryCliente.findAll();
			obterTodos.forEach(System.out::println);

			System.out.println("Buscando por nome");
			repositoryCliente.findByNome("Vinicius atualizado").forEach(System.out::println);
//
			System.out.println("Deletando clientes");

			repositoryCliente.findAll().forEach(c -> {
				repositoryCliente.delete(c);
			});

			obterTodos = repositoryCliente.findAll();
			if(obterTodos.isEmpty()){
				System.out.println("Nenhum cliente cadastrado");
			}else{
				obterTodos.forEach(System.out::println);
			}
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
