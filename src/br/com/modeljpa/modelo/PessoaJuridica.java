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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

/**
 *
 * @author t1076986
 */
@Entity
@Table(name="pessoa_juridica")
public class PessoaJuridica extends Pessoa implements Serializable{
    @NotNull(message="O IE não pode ser nulo")
    @NotBlank(message="O IE deve ser preenchido")
    @Length(max = 12, message = "O IE não pode ter mais de {max} caracteres")
    @Column(name="ie",length = 12, nullable = false)
    private String IE;
    @NotNull(message="O CNPJ não pode ser nulo")
    @NotBlank(message="O CNPJ deve ser preenchido")
    @Length(max = 14, message = "O CNPJ não pode ter mais de {max} caracteres")
    @CNPJ(message = "O CNPJ deve ser válido")
    @Column(name="cnpj",length = 14, nullable = false)
    private String CNPJ;
    

    public PessoaJuridica() {
    }
    
    

    public String getIe() {
        return IE;
    }

    public void setIe(String ie) {
        this.IE = ie;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

  
}
