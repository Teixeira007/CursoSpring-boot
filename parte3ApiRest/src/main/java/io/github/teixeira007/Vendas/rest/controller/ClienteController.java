package io.github.teixeira007.Vendas.rest.controller;

import io.github.teixeira007.Vendas.domain.entity.Cliente;
import io.github.teixeira007.Vendas.domain.repositorio.RepositoryCliente;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private RepositoryCliente repositoryCliente;

    public ClienteController(RepositoryCliente repositoryCliente) {
        this.repositoryCliente = repositoryCliente;
    }

    @GetMapping("{id}")
    public Cliente getClienteById (@PathVariable Integer id){
        return repositoryCliente.
                findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente){
        return repositoryCliente.save(cliente);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){

       Cliente cliente = repositoryCliente.findById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                       "Cliente não encontrado"));

       repositoryCliente.delete(cliente);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Cliente cliente){

        repositoryCliente.findById(id)
                .map(c -> {
                    cliente.setId(c.getId());
                    repositoryCliente.save(cliente);
                    return c;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));
    }

    @GetMapping
    public List<Cliente> find(Cliente filtro){

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);


        Example example = Example.of(filtro, matcher);
        return repositoryCliente.findAll(example);

    }
}
