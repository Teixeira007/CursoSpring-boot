package io.github.teixeira007.Vendas.rest.controller;

import io.github.teixeira007.Vendas.domain.entity.ItemPedido;
import io.github.teixeira007.Vendas.domain.entity.Pedido;
import io.github.teixeira007.Vendas.domain.service.PedidoService;
import io.github.teixeira007.Vendas.rest.dto.InformacoesItemPedidoDTO;
import io.github.teixeira007.Vendas.rest.dto.InformacoesPedidoDTO;
import io.github.teixeira007.Vendas.rest.dto.ItemPedidoDTO;
import io.github.teixeira007.Vendas.rest.dto.PedidoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO dto){
        Pedido pedido = pedidoService.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return pedidoService
                .obterPedidoCompleto(id)
                .map(p -> converter(p) )
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    private InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO
                .builder()
                .cpf(pedido.getCliente().getCpf())
                .dataPedido(pedido.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .codigo(pedido.getId())
                .total(pedido.getTotal())
                .nomeCliente(pedido.getCliente().getNome())
                .itens(converter(pedido.getItens()))
                .build();
    }

    private List<InformacoesItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens.stream().map(
                item -> InformacoesItemPedidoDTO
                        .builder().descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
                ).collect(Collectors.toList());
    }
}
