package io.github.teixeira007.Vendas;

import io.github.teixeira007.Vendas.domain.entity.Cliente;
import io.github.teixeira007.Vendas.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes){
		return args -> {
			Cliente cliente = new Cliente("Vinicius");
			Cliente cliente1 = new Cliente("Joao");

			System.out.println("Salvando os clientes");
			clientes.salvar(cliente);
			clientes.salvar(cliente1);


			System.out.println("Listando os clientes");
			List<Cliente> obterTodos = clientes.obterTodos();
			obterTodos.forEach(System.out::println);

			System.out.println("Atualizando os clientes");

			obterTodos.forEach(c -> {
				c.setNome(c.getNome() + " atualizado");
				clientes.atualizar(c);
			});


			System.out.println("Listando os clientes atualizados");
			obterTodos = clientes.obterTodos();
			obterTodos.forEach(System.out::println);

			System.out.println("Buscando por nome");
			clientes.obterPorNome("Vini").forEach(System.out::println);
//
			System.out.println("Deletando clientes");

			clientes.obterTodos().forEach(c -> {
				clientes.deletar(c);
			});

			obterTodos = clientes.obterTodos();
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
