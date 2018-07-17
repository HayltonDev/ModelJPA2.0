
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
@Table(name = "foto")
public class Foto implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_foto", sequenceName = "seq_foto_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_foto", strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NotNull(message ="O nome deve ser preenchido")
    @NotBlank(message ="O nome não pode estar em branco")
    @Length(max = 50, message = "O nome pode conter no máximo {max} caracteres")
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;
    @NotNull(message ="A descrição deve ser preenchida")
    @NotBlank(message ="A descrição não pode estar em branca")
    @Length(max = 50, message = "A descrição pode conter no máximo {max} caracteres")
    @Column(name = "descricao", length = 50, nullable = false)
    private String descricao;
    @Lob
    private byte[] arquivo;
    @NotNull(message = "O produto deve ser informado")
    @ManyToOne
    @JoinColumn(name = "produto", referencedColumnName = "id", nullable = false)
    private Produto produto;

    public Foto() {
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
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Foto other = (Foto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
}
