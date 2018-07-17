package br.com.modeljpa.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Haylton
 */
@Embeddable
public class CompraID implements Serializable{
    
    @NotNull(message = "O numero da nota não pode ser nulo")
    @Column(name = "numero_nota", nullable = false)
    private Integer numeroNota;
    
    @NotNull(message = "Pessoa juridica deve ser informada")
    @ManyToOne
    @JoinColumn(name = "pessoa_juridica", referencedColumnName = "id", nullable = false)
    private PessoaJuridica pessoaJuridica;

    public CompraID() {
    }
    
    
    public Integer getNumeroNota() {
        return numeroNota;
    }

    public void setNumeroNota(Integer numeroNota) {
        this.numeroNota = numeroNota;
    }

    public PessoaJuridica getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
        this.pessoaJuridica = pessoaJuridica;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.numeroNota);
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
        final CompraID other = (CompraID) obj;
        if (!Objects.equals(this.numeroNota, other.numeroNota)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
