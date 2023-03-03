package io.github.teixeira007.Vendas.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

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
    @NotEmpty(message = "Campo nome é obrigatório.")
    private String nome;
    @Column(name = "cpf", length = 11)
    @NotEmpty(message = "Campo CPF é obrigatório.")
    @CPF(message = "Informe um CPF valido.")
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> pedidos;



    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
