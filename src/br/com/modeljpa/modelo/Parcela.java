package br.com.modeljpa.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Haylton
 */
@Entity
@Table(name = "parcela")
public class Parcela implements Serializable{
    
    @EmbeddedId
    private ParcelaID parcelaID;
    @NotNull(message = "O valor deve ser informado")
    @Min(value = 0, message = "O valor não pode ser negativo")
    @Column(name = "valor", nullable = false, columnDefinition = "decimal(12,2)" )
    private Double valor;
    @NotNull(message = "O vencimento não pode ser nulo")
    @Temporal(TemporalType.TIMESTAMP) //alterei de data para TimeStamp
    @Column(name = "vencimento",nullable = false)
    private Calendar vencimento;
    @Min(value = 0, message = "O valor do pagamento não pode ser negativo")
    @Column(name = "valor_pagamento", columnDefinition = "decimal(12,2)" )
    private Double valorPagamento;
    @Temporal(TemporalType.TIMESTAMP) //alterei de data para TimeStamp
    @Column(name = "data_pagamento")
    private Calendar dataPagamento;
   
    
    //falta gerar métodos para Parcela

    public Parcela() {
    }

    public ParcelaID getParcelaID() {
        return parcelaID;
    }

    public void setParcelaID(ParcelaID parcelaID) {
        this.parcelaID = parcelaID;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Calendar getVencimento() {
        return vencimento;
    }

    public void setVencimento(Calendar vencimento) {
        this.vencimento = vencimento;
    }

    public Double getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(Double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public Calendar getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Calendar dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.parcelaID);
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
        final Parcela other = (Parcela) obj;
        if (!Objects.equals(this.parcelaID, other.parcelaID)) {
            return false;
        }
        return true;
    }
    
    
}
