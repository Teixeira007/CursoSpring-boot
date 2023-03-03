package io.github.teixeira007.Vendas.rest.controller;

import io.github.teixeira007.Vendas.exception.PedidoNaoEncontradoException;
import io.github.teixeira007.Vendas.exception.RegradeNegocioException;
import io.github.teixeira007.Vendas.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApllicationControllerAdvice {

    @ExceptionHandler(RegradeNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegradeNegocioException(RegradeNegocioException ex){
        String mensagem = ex.getMessage();
        return new ApiErrors(mensagem);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlerPedidoNotFoundException(PedidoNaoEncontradoException ex){
        return new ApiErrors(ex.getMessage());
    }

}
