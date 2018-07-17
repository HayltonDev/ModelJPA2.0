/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.modeljpa.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Haylton
 */

@Entity
@Table(name="endereco")
public class Endereco implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_endereco", sequenceName = "seq_endereco_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_endereco",strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "O nickname não pode ser nulo")
    @NotBlank(message = "O nickname deve ser preenchido")
    @Length(max = 20, message = "O nickname tem que ter no tamanho máximo de {max} caracteres")
    @Column(name = "nickname", nullable = false, length = 20)
    private String nickname;
    
    @NotNull(message = "O endereco não pode ser nulo")
    @NotBlank(message = "O endereco deve ser preenchido")
    @Length(max = 50, message = "O endereco tem que ter no tamanho máximo de {max} caracteres")
    @Column(name = "endereco", nullable = false, length = 50)
    private String endereco;
    
    @NotNull(message = "O numero não pode ser nulo")
    @NotBlank(message = "O numero deve ser preenchido")
    @Length(max = 10, message = "O numero tem que ter no tamanho máximo de {max} caracteres")
    @Column(name = "numero", nullable = false, length = 10)
    private String numero;

    @Length(max = 10, message = "O complemento tem que ter no tamanho máximo de {max} caracteres")
    @Column(name = "complemento",  length = 20)
    private String complemento;
    
    @NotNull(message = "O cep não pode ser nulo")
    @NotBlank(message = "O cep deve ser preenchido")
    @Length(max = 9, message = "O cep tem que ter no tamanho máximo de {max} caracteres")
    @Column(name = "cep", nullable = false, length = 9)
    private String cep;
    
    @NotNull(message = "O bairro não pode ser nulo")
    @NotBlank(message = "O bairro deve ser preenchido")
    @Length(max = 40, message = "O bairro tem que ter no tamanho máximo de {max} caracteres")
    @Column(name = "bairro", nullable = false, length = 40)
    private String bairro;
    
    @NotNull(message = "A referencia não pode ser nula")
    @NotBlank(message = "A referencia deve ser preenchida")
    @Column(name = "referencia", nullable = false, columnDefinition = "text" )
    private String referencia;
    
    @NotNull(message = "A pessoa deve ser informada")
    @ManyToOne
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id", nullable = false)
    @ForeignKey(name="fk_pessoa_id")
    private Pessoa pessoa;
    
    @NotNull(message = "O tipo de endereco  deve ser informado")
    @ManyToOne
    @JoinColumn(name = "tipo_endereco", referencedColumnName = "id", nullable = false)
    @ForeignKey(name="fk_tipo_endereco_id")
    private TipoEndereco tipoEndereco;

    public Endereco() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public TipoEndereco getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(TipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Endereco other = (Endereco) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
}
