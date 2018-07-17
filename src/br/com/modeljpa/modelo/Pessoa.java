/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.modeljpa.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Haylton
 */

@Entity
@Inheritance(strategy =InheritanceType.JOINED )//informa para jpa que essa classe pode oferecer herança com essa anotação @Inheritance  //a estratégia single_table é uma unica tabela pessoa, porem com atributos de pessoas jurifdicas e fisicas, caso quando for persistir for uma pessoa juridica, os atributos de pessoa fisica fica em branco.
@Table(name = "pessoa")
public class Pessoa implements Serializable{ //já strategy JOINED cria uma tabela para cada tipo de pessoa
    @Id
    @SequenceGenerator(name = "seq_pessoa", sequenceName = "seq_pessoa_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_pessoa", strategy = GenerationType.SEQUENCE)
    private Integer Id;
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode ser em branco")
    @Length(max = 50, message = "O nome não pode ter mais de {max} caracteres")
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    @NotNull(message = "O email não pode ser nulo")
    @NotBlank(message = "O email não pode ser em branco")
    @Length(max = 50, message = "O email não pode ter mais de {max} caracteres")
    @Column(name = "email", length = 50, nullable = false)
    private String email;
    @NotNull(message = "O telefone não pode ser nulo")
    @NotBlank(message = "O telefone não pode ser em branco")
    @Length(max = 14, message = "O telefone não pode ter mais de {max} caracteres")
    @Column(name = "telefone", length = 14, nullable = false)
    private String telefone;
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY ) //o mappedBy eu tenho que informar qual o nome do atributo na classe endereco que estabele relação com a classe pessoa, no caso eu informo o nome do atributo na classe e não o nome que está no BD
    List<Endereco> enderecos = new ArrayList<>(); //o cascade ali em cima são as operações de persisterncia que serão propagadas para os filhos
                                                    //Fetchtype.EAGER serve para que quando eu carregar a pessoa, eu carrego todos os endereços da pessoa, já o lazy eu carrego tardiamente a lista somente quando eu
                                                    //relmente acessar a classe de enderecos que vai ser carregada. Ou seja, no caso da EAGER, se eu tenho 100 pessoas e cada uma tem 10 endereços, no total são 1000, vai pesar, agora com lazy eu acesso pessoa, carrega uma e seus 10 endereços
    

    public Pessoa() {
    }
    
    public void adicionarEndereco(Endereco endereco){
        endereco.setPessoa(this);
        this.enderecos.add(endereco);
    }
    
    public void removerEndereco(int index){
        this.enderecos.remove(index);
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.Id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.Id, other.Id)) {
            return false;
        }
        return true;
    }
    
    
            
}
