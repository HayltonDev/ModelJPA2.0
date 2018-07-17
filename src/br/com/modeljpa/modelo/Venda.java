package br.com.modeljpa.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
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
@Table(name = "venda")
public class Venda implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_venda", sequenceName = "seq_venda_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_venda", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "A data da venda não pode ser nula")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_venda", nullable = false)
    private Calendar dataVenda;
    @NotNull(message = "O valor total não pode ser nulo")
    @Min(value = 0, message = "O valor total não pode ser negativo")
    @Column(name = "valor_total", columnDefinition = "numeric(10,2)", nullable = false)
    private Double valorTotal;
    @NotNull(message = "A quantidade de parcelas não pode ser nula")
    @Min(value = 0, message = "As quantidade de parcelas não pode ser negativa")
    @Column(name = "parcelas", nullable = false)
    private Integer parcelas;
    @NotNull(message = "A pessoa fisica não pode ser nula")
    @ManyToOne
    @JoinColumn(name = "pessoa_fisica", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_pessoa_fisica")
    private PessoaFisica pessoaFisica;
    @OneToMany(  mappedBy = "venda"  ,cascade = CascadeType.ALL, fetch =FetchType.LAZY,orphanRemoval = true )
    private List<VendaItem> items = new ArrayList<>();
    
    @OneToMany(mappedBy = "parcelaID.venda", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Parcela> listaParcelas = new ArrayList<>();
    
    public Venda() { // contador para adicionar ou remover intens
        this.valorTotal = 0.0;
        
    }
    
    //método para gerar as parcelas da venda
    public void gerarParcelas(){
        System.out.println("Entrou no gerarParcelas de venda");
        Double valorParcela = this.valorTotal /this.parcelas;
        for(int i = 1; i <= this.parcelas; i++){
            Parcela parcela = new Parcela();
            ParcelaID id = new ParcelaID();
            id.setNumero(i);
            id.setVenda(this); //this aqui é a própria venda
            parcela.setParcelaID(id);
            parcela.setValor(valorParcela);
            Calendar vencimento = (Calendar) this.dataVenda.clone(); //pego a data original, clonando sem alterar
            vencimento.add(Calendar.MONTH, i);
            parcela.setVencimento(vencimento);
            this.listaParcelas.add(parcela);
        }
    }
    
    //métodos para adicionar e remover vendasITens dessa lista. Esses métodos são operações de adicionar e remover itens lá na página sendo que ainda n~~ao tenho um ID
    public void adicionarItem(VendaItem vendaItem){
       vendaItem.setVenda(this);
       this.valorTotal += vendaItem.getValorTotal();
       this.items.add(vendaItem);
    }
    
    public void removerItem(int index){
        VendaItem vendaItem = this.items.get(index); //vai devolver o indice da lista
        this.valorTotal -= vendaItem.getValorTotal();
        this.items.remove(index);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Calendar dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public List<VendaItem> getItems() {
        return items;
    }

    public void setItems(List<VendaItem> items) {
        this.items = items;
    }

    public List<Parcela> getListaParcelas() {
        return listaParcelas;
    }

    public void setListaParcelas(List<Parcela> listaParcelas) {
        this.listaParcelas = listaParcelas;
    }

    public PessoaFisica getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(PessoaFisica pessoaFisica) {
        this.pessoaFisica = pessoaFisica;
    }
    
    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Venda other = (Venda) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
