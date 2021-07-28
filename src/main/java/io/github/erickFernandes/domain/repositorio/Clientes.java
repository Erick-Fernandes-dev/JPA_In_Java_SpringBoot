package io.github.erickFernandes.domain.repositorio;

import io.github.erickFernandes.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {
    //ESSA CLASSE FAZ OPERAÇÕES COM BASE DE DADOS.

    //private static String INSERT = "insert into cliente (nome) values (?) ";
    private static String SELECT_ALL = "SELECT * FROM CLIENTE ";
    private static String UPDATE = "update cliente set nome = ? where id = ?";
    private static String DELETE = "delete from cliente where id = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    // EntityManager e uma interface que faz todas as operacoes na base com as
    // entidades.

    @Transactional// vai gerar uma transacao na base de dados
    public Cliente salvar(Cliente cliente) {
        this.entityManager.persist(cliente);
        return cliente;

    }


    public Cliente atualizar(Cliente cliente) {

        jdbcTemplate.update(UPDATE, new Object[] {
                cliente.getNome(),
                cliente.getId()
        });

        return cliente;
    }

    public void deletar(Cliente cliente) {
        deletar(cliente.getId());

    }

    public void deletar(Integer id) {
        this.jdbcTemplate.update(DELETE, new Object[] {id});
    }

    public List<Cliente> obterTodos() {

        return jdbcTemplate.query(SELECT_ALL, obterClienteMapper());

    }

    public List<Cliente> buscarPorNome(String nome) {
        return this.jdbcTemplate.query(
                SELECT_ALL.concat("where nome like ? "),
                new Object[]{"%" + nome + "%"},
                obterClienteMapper());
    }

    private RowMapper<Cliente> obterClienteMapper() {

        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {

                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }

    // ctrl + alt + m --> trocar para metodo
    //getString --> Serve para pegar um valor String de uma coluna que
    //veio lá do resultado do SELECT

    //resultSet --> tem todo resultado do bando de dados

    //RowMapper --> serve para mapear o resultado do banco de dados para uma classe
    //update --> serve para excutar scripts sql de atualização, inserção e deleção.
}
