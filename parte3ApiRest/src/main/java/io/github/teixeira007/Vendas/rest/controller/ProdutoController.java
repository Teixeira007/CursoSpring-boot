package io.github.teixeira007.Vendas.rest.controller;

import io.github.teixeira007.Vendas.domain.entity.Produto;
import io.github.teixeira007.Vendas.domain.repositorio.RepositoryProduto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private RepositoryProduto repositoryProduto;

    public ProdutoController(RepositoryProduto repositoryProduto) {
        this.repositoryProduto = repositoryProduto;
    }

    @GetMapping("/{id}")
    public Produto getProdutoId(@PathVariable Integer id){
        return repositoryProduto.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody @Valid Produto produto){
        return repositoryProduto.save(produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        Produto produto = repositoryProduto.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado"));
        repositoryProduto.delete(produto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Produto produto){
        repositoryProduto.findById(id)
                .map(p -> {
                    produto.setId(p.getId());
                    repositoryProduto.save(produto);
                    return p;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado"));
    }

    @GetMapping
    public List<Produto> find(Produto filtro){

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return repositoryProduto.findAll(example);
    }

}
