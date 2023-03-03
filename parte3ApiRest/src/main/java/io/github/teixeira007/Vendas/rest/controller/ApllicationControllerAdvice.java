package io.github.teixeira007.Vendas.rest.controller;

import io.github.teixeira007.Vendas.exception.PedidoNaoEncontradoException;
import io.github.teixeira007.Vendas.exception.RegradeNegocioException;
import io.github.teixeira007.Vendas.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handlerMhetodNotValidException(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream().map(erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());

        return new ApiErrors(errors);
    }

}
