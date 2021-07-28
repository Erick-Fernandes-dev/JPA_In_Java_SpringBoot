package io.github.erickFernandes;

import io.github.erickFernandes.domain.entity.Cliente;
import io.github.erickFernandes.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

    @Bean
    public CommandLineRunner executar(@Autowired Clientes clientes) {

        return args -> {

            System.out.println("Salvando Cliente");

            clientes.salvar(new Cliente("Erick Fernandes"));
            clientes.salvar(new Cliente("Jose Gabriel"));

            List<Cliente> obterTodosClientes = clientes.obterTodos();
            obterTodosClientes.forEach(System.out::println);

            System.out.println("Atualizando Cliente");

            obterTodosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado");
                clientes.atualizar(c);
            });

            obterTodosClientes = clientes.obterTodos();
            obterTodosClientes.forEach(System.out::println);

            System.out.println("Buscando cliente");

            clientes.buscarPorNome("Gab").forEach(System.out::println);

            System.out.println("Deletando clientes");
            clientes.obterTodos().forEach(c -> {
                    clientes.deletar(c);
            });


            obterTodosClientes = clientes.obterTodos();

            if (obterTodosClientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado.");
            } else {
                obterTodosClientes.forEach(System.out::println);
            }

        };


    }


}