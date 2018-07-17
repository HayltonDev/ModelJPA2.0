package br.com.modeljpa.modelo;

import java.io.Serializable;
import java.util.Calendar;
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
public class ParcelaID implements Serializable{
    
    @NotNull(message = "O numero da parcela não pode ser nula")
    @Column(name = "numero", nullable = false)
    private Integer numero;
    
    @NotNull(message = "A venda deve ser informada")
    @ManyToOne
    @JoinColumn(name = "venda", referencedColumnName = "id", nullable = false)
    private Venda venda;
    

    public ParcelaID() {
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }
    
    

  
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.numero);
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
        final ParcelaID other = (ParcelaID) obj;
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return true;
    }
    
    
}
