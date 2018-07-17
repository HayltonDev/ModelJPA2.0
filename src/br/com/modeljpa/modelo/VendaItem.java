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
@Table(name = "venda_item")
public class VendaItem implements Serializable{
    
    @Id
    @SequenceGenerator(name="seq_venda_itens", schema = "seq_venda_itens_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_venda_itens", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "A quantidade total deve ser informada")
    @Min(value = 0, message = "A quantidade total não pode ser negativa")
    @Column(name = "quantidade", nullable = false, columnDefinition = "decimal(12,2)" )
    private Double quantidade;
    @NotNull(message = "O valor unitario deve ser informado")
    @Min(value = 0, message = "O valor unitario  não pode ser negativo")
    @Column(name = "valor_unitario", nullable = false, columnDefinition = "decimal(12,2)" )
    private Double valorUnitario;
    @NotNull(message = "O valor total deve ser informado")
    @Min(value = 0, message = "O valor total não pode ser negativo")
    @Column(name = "valor_total", nullable = false, columnDefinition = "decimal(12,2)" )
    private Double valorTotal;
    @NotNull(message = "A venda não pode ser nula")
    @ManyToOne
    @JoinColumn(name="venda",referencedColumnName = "id", nullable = false)
    private Venda venda;
    @NotNull(message = "O produto não pode ser nulo")
    @ManyToOne
    @JoinColumn(name="produto",referencedColumnName = "id", nullable = false)
    private Produto produto;
    public VendaItem() {
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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
        hash = 11 * hash + Objects.hashCode(this.id);
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
        final VendaItem other = (VendaItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
