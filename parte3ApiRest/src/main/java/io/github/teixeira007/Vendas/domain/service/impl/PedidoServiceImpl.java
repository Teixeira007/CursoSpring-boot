package io.github.teixeira007.Vendas.domain.service.impl;

import io.github.teixeira007.Vendas.domain.entity.Cliente;
import io.github.teixeira007.Vendas.domain.entity.ItemPedido;
import io.github.teixeira007.Vendas.domain.entity.Pedido;
import io.github.teixeira007.Vendas.domain.entity.Produto;
import io.github.teixeira007.Vendas.domain.repositorio.RepositoryCliente;
import io.github.teixeira007.Vendas.domain.repositorio.RepositoryItemPedido;
import io.github.teixeira007.Vendas.domain.repositorio.RepositoryPedido;
import io.github.teixeira007.Vendas.domain.repositorio.RepositoryProduto;
import io.github.teixeira007.Vendas.domain.service.PedidoService;
import io.github.teixeira007.Vendas.exception.RegradeNegocioException;
import io.github.teixeira007.Vendas.rest.dto.ItemPedidoDTO;
import io.github.teixeira007.Vendas.rest.dto.PedidoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final RepositoryPedido repositoryPedido;
    private final RepositoryCliente repositoryCliente;
    private final RepositoryProduto repositoryProduto;
    private final RepositoryItemPedido repositoryItemPedido;



    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto){

        Integer idCliente = dto.getCliente();
        Cliente cliente = repositoryCliente.
                findById(idCliente).
                orElseThrow(() -> new RegradeNegocioException("codigo do cliente não encontrado"));

        Pedido pedido = new Pedido();

        pedido.setTotal(dto.getTotal());
        pedido.setData(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemPedidos = converterItemPedido(pedido, dto.getItems());
        repositoryPedido.save(pedido);
        repositoryItemPedido.saveAll(itemPedidos);
        pedido.setItens(itemPedidos);

        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repositoryPedido.findByIdFetchItens(id);
    }

    private List<ItemPedido> converterItemPedido(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegradeNegocioException("Lista de items vazia");
        }

        return items.stream().map(dto -> {

            Integer idProduto = dto.getProduto();
            Produto produto = repositoryProduto
                    .findById(idProduto)
                    .orElseThrow(()-> new RegradeNegocioException("codigo do produto não encontrado: "+idProduto));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(dto.getQuantidade());
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);

            return itemPedido;

        }).collect(Collectors.toList());
    }
}
