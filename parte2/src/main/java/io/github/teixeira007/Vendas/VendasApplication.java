package io.github.teixeira007.Vendas;

import io.github.teixeira007.Vendas.domain.entity.Cliente;
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
	public CommandLineRunner init(@Autowired RepositoryClientesJPA repositoryClientesJPA){
		return args -> {
			Cliente cliente = new Cliente("Vinicius");
			Cliente cliente1 = new Cliente("Joao");

			System.out.println("Salvando os clientes");
			repositoryClientesJPA.salvar(cliente);
			repositoryClientesJPA.salvar(cliente1);


			System.out.println("Listando os clientes");
			List<Cliente> obterTodos = repositoryClientesJPA.obterTodos();
			obterTodos.forEach(System.out::println);

			System.out.println("Atualizando os clientes");

			obterTodos.forEach(c -> {
				c.setNome(c.getNome() + " atualizado");
				repositoryClientesJPA.atualizar(c);
			});


			System.out.println("Listando os clientes atualizados");
			obterTodos = repositoryClientesJPA.obterTodos();
			obterTodos.forEach(System.out::println);

			System.out.println("Buscando por nome");
			repositoryClientesJPA.obterPorNome("Vini").forEach(System.out::println);
//
			System.out.println("Deletando clientes");

			repositoryClientesJPA.obterTodos().forEach(c -> {
				repositoryClientesJPA.deletar(c);
			});

			obterTodos = repositoryClientesJPA.obterTodos();
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
