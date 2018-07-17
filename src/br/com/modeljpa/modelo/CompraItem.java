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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;

/**
 *
 * @author Haylton
 */
@Entity
@Table(name = "compra_item")
public class CompraItem implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_compra_item", sequenceName = "seq_compra_item_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_compra_item", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "A quantidade não pode ser nula")
    @Min(value = 0, message = "A quantidade não pode ser negativa")
    @Column(name = "quantidade", nullable = false, columnDefinition = "numeric(10,2)")
    private Double quantidade;
    @NotNull(message = "O valor unitário não pode ser nulo")
    @Min(value = 0, message = "O valor unitário não pode ser negativo")
    @Column(name = "valorUnitario", nullable = false, columnDefinition = "numeric(10,2)")
    private Double valorUnitario;
    @NotNull(message = "O valor total não pode ser nulo")
    @Min(value = 0, message = "O valor unitário não pode ser negativo")
    @Column(name = "valorTotal", nullable = false, columnDefinition = "numeric(10,2)")
    private Double valorTotal;
    
    @ManyToOne
    @NotNull(message = "O produto deve ser informado")
    @JoinColumn(name = "produto", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_produto")
    private Produto produto;
    
   //não preciso colocar JoinColum aqui pq a própria JPA vai fazer isso por eu estar usando uma classe Embeddable que é a chave composta já faz o joincolumn AQUI
    @NotNull(message = "A compra não pode ser nula")
    @ManyToOne
    private Compra compra;

    public CompraItem() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final CompraItem other = (CompraItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
