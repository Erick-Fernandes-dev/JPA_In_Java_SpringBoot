package io.github.erickFernandes.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

    //Mappeando a entidade cliente para JPA

    // essa annotation define qual e a primary key dessa entidade
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 100)
    private String nome;

    public Cliente() {

    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setIde(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString() {
        return "Cliente(" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
