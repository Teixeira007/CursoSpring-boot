package io.github.teixeira007.Vendas.domain.repositorio;

import io.github.teixeira007.Vendas.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static String INSERT = "INSERT INTO CLIENTE (NOME) VALUES (?)";
    private static String SELECT_ALL = "SELECT * FROM CLIENTE";
    private static String UPDATE = "UPDATE CLIENTE SET NOME = ? WHERE ID = ?";
    private static String DELETE = "DELETE FROM CLIENTE WHERE ID = ?";

    public Cliente salvar(Cliente cliente){
        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    public Cliente atualizar(Cliente cliente){
        jdbcTemplate.update(UPDATE, new Object[]{
                cliente.getNome(),
                cliente.getId()
        } );
        return cliente;
    }

    public void deletar(Cliente cliente){
        deletar(cliente.getId());
    }

    public void deletar(Integer id){
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    public List<Cliente> obterPorNome(String nome){
        return jdbcTemplate.query(SELECT_ALL.concat(" WHERE NOME LIKE ?"),
                new Object[]{"%" +nome+ "%"},
                obterPorMapper());
    }

    public List<Cliente> obterTodos(){
        return jdbcTemplate.query(SELECT_ALL, obterPorMapper());
    }
    public RowMapper<Cliente> obterPorMapper(){
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }
}
