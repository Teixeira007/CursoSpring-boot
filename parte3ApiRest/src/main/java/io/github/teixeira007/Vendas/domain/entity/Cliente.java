package io.github.teixeira007.Vendas.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;
    @Column(name = "cpf", length = 11)
    private String cpf;

    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> pedidos;



    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
