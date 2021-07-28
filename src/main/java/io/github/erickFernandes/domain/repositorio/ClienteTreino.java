package io.github.erickFernandes.domain.repositorio;

import io.github.erickFernandes.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClienteTreino {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    //INSERIR OU ADCIONAR
    private static String INSERT = "insert into cliente (nome) values (?)";

    //SELECIONAR TODOS
    private static String SELECT_ALL = "SELECT * FROM CLIENTE";

    //ATUALIZAR OU ALTERAR
    private static String UPDATE = "update set nome = ? where id = ?";

    //APAGAR
    private static String DELETE = "delete from clinte where id = ?";


    public Cliente salvar(Cliente cliente) {
        this.jdbcTemplate.update(INSERT, new Object[]{
                cliente.getNome()
        });

        return cliente;
    }

    public Cliente atualizar(Cliente cliente) {

        this.jdbcTemplate.update(UPDATE, new Object[]{
                cliente.getNome(),
                cliente.getId()
        });

        return cliente;
    }

    public void delete(Cliente cliente) {
        delete(cliente.getId());

    }

    public void delete(Integer id) {
        this.jdbcTemplate.update(DELETE, new Object[]{id});

    }

    public List<Cliente> obterTodos(Cliente cliente) {

        return this.jdbcTemplate.query(SELECT_ALL, new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {

                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");

                return new Cliente(id, nome);
            }
        });
    }

}

