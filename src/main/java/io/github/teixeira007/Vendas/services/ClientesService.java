package io.github.teixeira007.Vendas.services;


import io.github.teixeira007.Vendas.model.Cliente;
import io.github.teixeira007.Vendas.repository.ClientesRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    private ClientesRepository repository;
    public  ClientesService(ClientesRepository repository){
        this.repository = repository;
    }

    public void salvarCliente(Cliente cliente){
        validarCliente(cliente);
        this.repository.persistir(cliente);

    }

    public void validarCliente(Cliente cliente){
        //validação
    }

}
