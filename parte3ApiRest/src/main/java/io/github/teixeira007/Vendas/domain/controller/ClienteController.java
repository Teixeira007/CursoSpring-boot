package io.github.teixeira007.Vendas.domain.controller;

import io.github.teixeira007.Vendas.domain.entity.Cliente;
import io.github.teixeira007.Vendas.domain.repositorio.RepositoryCliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class ClienteController {

    private RepositoryCliente repositoryCliente;

    public ClienteController(RepositoryCliente repositoryCliente) {
        this.repositoryCliente = repositoryCliente;
    }

    @GetMapping("api/clientes/{id}")
    @ResponseBody
    public ResponseEntity getClienteById (@PathVariable Integer id){
        Optional<Cliente> cliente = repositoryCliente.findById(id);

        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping("api/clientes")
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente){
        Cliente clienteSalvo = repositoryCliente.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("api/clientes/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id){
        Optional<Cliente> cliente = repositoryCliente.findById(id);

        if(cliente.isPresent()){
            repositoryCliente.delete(cliente.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("api/clientes/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Cliente cliente){

        return repositoryCliente.findById(id)
                                .map(c -> {
                                    cliente.setId(c.getId());
                                    repositoryCliente.save(cliente);
                                    return ResponseEntity.noContent().build();
                                }).orElseGet( () ->  ResponseEntity.notFound().build());
    }
}
