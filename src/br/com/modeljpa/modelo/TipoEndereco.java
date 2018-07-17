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
@Table(name="tipo_endereco")
public class TipoEndereco implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_tipoEndereco", sequenceName = "seq_tipoEndereco_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_tipoEndereco", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @NotNull(message = "A descrição não pode ser nula")
    @NotBlank(message = "A descrição deve ser preenchida")
    @Length(max = 50, message = "A descrição tem o tamanho máximo de {max} caracteres")
    @Column(name = "descricao", nullable = false, length = 50)
    private String descricao;
    
    public TipoEndereco() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final TipoEndereco other = (TipoEndereco) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
