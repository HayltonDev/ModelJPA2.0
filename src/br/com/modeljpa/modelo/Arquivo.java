package br.com.modeljpa.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "arquivo")
public class Arquivo implements Serializable{
    
    @Id
    @SequenceGenerator(name = "seq_arquivo", sequenceName = "seq_arquivo_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_arquivo", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message = "O nome do arquivo deve ser preenchido")
    @NotBlank(message = "O nome do arquivo não pode ser deixa em branco")
    @Length(max = 50, message = "O nome do arquivo pode ter no máximo {max} caracteres")
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;
    @NotNull(message = "A descrição do arquivo deve ser preenchida")
    @NotBlank(message = "A descrição do arquivo não pode ser deixa em branca")
    @Length(max = 50, message = "A descrição do arquivo pode ter no máximo {max} caracteres")
    @Column(name = "descricao", nullable = false, length = 50)
    private String descricao;
    @Lob
    @Column(name = "arquivo")
    private byte[] arquivo;
    @NotNull(message = "O nome do produto deve ser informado")
    @ManyToOne
    @JoinColumn(name = "produto", referencedColumnName = "id", nullable = false)
    private Produto produto;

    public Arquivo() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
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
        final Arquivo other = (Arquivo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
