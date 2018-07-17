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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Haylton
 */
@Entity
@Table(name = "produto")
public class Produto implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_produto", sequenceName ="seq_produto_id", allocationSize = 1 )
    @GeneratedValue(generator = "seq_produto", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome deve ser preenchido")
    @Length(max = 50, message = "O tamanho máximo é de {max} caracteres")
    @Column(name = "nome", length = 50, nullable = false)
    private String nome; 
    @NotNull(message = "O preco não pode ser nulo")
    @Min(value = 0, message = "O preco não pode ser negativo")
    @Column(name = "preco", nullable = false,columnDefinition = "numeric(10,2)" )
    private Double preco;
    @NotNull(message = "A quantidade de estoque não pode ser nula")
    @Min(value = 0, message = "A quantidade de estoque não pode ser negativa")
    @Column(name = "quantidade_estoque", nullable = false,columnDefinition = "numeric(10,2)" )
    private Double quantidadeEstoque;
    @NotNull(message = "A descricao não pode ser nula")
    @NotBlank(message = "A descricao deve ser preenchida")
    @Length(max = 200, message = "O tamanho máximo é de {max} caracteres")
    @Column(name = "descricao", length = 200, nullable = false)
    private String descricao;
    
    @ManyToOne
    @JoinColumn(name = "categoria", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_categoria")
    private Categoria categoria;
    
    @ManyToOne
    @JoinColumn(name = "marca", referencedColumnName = "id", nullable = false)
    @ForeignKey(name = "fk_marca")
    private Marca marca;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "desejos",
            joinColumns = 
            @JoinColumn(name = "produto", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = 
            @JoinColumn(name = "pessoa_fisica", referencedColumnName = "id", nullable = false), 
            uniqueConstraints = {@UniqueConstraint(columnNames = {"pessoa_fisica","produto"})})
    private List<PessoaFisica> desejam = new ArrayList<>();
    
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "fornecimento",
            joinColumns =
            @JoinColumn(name = "produto", referencedColumnName = "id", nullable = false),
            inverseJoinColumns = 
            @JoinColumn(name = "pessoa_juridica", referencedColumnName = "id", nullable = false),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"pessoa_juridica", "produto"})}
            )
    private List<PessoaJuridica> fornecimento = new ArrayList<>();
    
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Arquivo> arquivos = new ArrayList<>(); //eu só tenho list do obj no lado onde o relacionamento é 1 quando há composição(diamante preenchido) ou oneToMany e asseta aponta para essa classe 
    
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Foto> fotos = new ArrayList<>();
    

    public Produto() {
    }
    
    
    public void adicionarArquivo(Arquivo arquivo){
        arquivo.setProduto(this);
        this.arquivos.add(arquivo);
    }
    
    public void removerArquivo(int index){
        this.arquivos.remove(index);
    }
    
    public void adicionarFoto(Foto foto){
        foto.setProduto(this);
        this.fotos.add(foto);
    }
    
    public void removerFoto(int index){
        this.fotos.remove(index);
    }

    public Integer getId() {
        return id;
    }
    

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Double quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<PessoaFisica> getDesejam() {
        return desejam;
    }

    public void setDesejam(List<PessoaFisica> desejam) {
        this.desejam = desejam;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public List<Arquivo> getArquivos() {
        return arquivos;
    }

    public void setArquivos(List<Arquivo> arquivos) {
        this.arquivos = arquivos;
    }

    public List<PessoaJuridica> getFornecimento() {
        return fornecimento;
    }

    public void setFornecimento(List<PessoaJuridica> fornecimento) {
        this.fornecimento = fornecimento;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }
    
    
    
    
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    
}
