package io.github.teixeira007.Vendas.domain.entity;

import io.github.teixeira007.Vendas.domain.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column(name = "total", length = 2, precision = 20)
    private BigDecimal total;
    @Column(name = "data_pedido")
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status;


    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;


}
