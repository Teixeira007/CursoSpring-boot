package io.github.teixeira007.Vendas;

import io.github.teixeira007.Vendas.domain.entity.Cliente;
import io.github.teixeira007.Vendas.domain.entity.Pedido;
import io.github.teixeira007.Vendas.domain.repositorio.RepositoryCliente;
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


	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}

}
