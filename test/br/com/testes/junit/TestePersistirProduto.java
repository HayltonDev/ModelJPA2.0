package br.com.testes.junit;

import br.com.modeljpa.jpa.EntityManagerUtil;
import br.com.modeljpa.modelo.Categoria;
import br.com.modeljpa.modelo.Marca;
import br.com.modeljpa.modelo.PessoaFisica;
import br.com.modeljpa.modelo.Produto;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestePersistirProduto {

    EntityManager em;

    public TestePersistirProduto() {
    }

    @Before
    public void setUp() {
        em = EntityManagerUtil.getEntityManager();
    }

    @After
    public void tearDown() {
        em.close();
    }

    @Test
    public void teste() {
        boolean exception = false;
        try {
            Produto produto = new Produto();
            produto.setCategoria(em.getReference(Categoria.class, 2));
            produto.setNome("Pc Lenovinho");
            produto.setMarca(em.getReference(Marca.class,5));
            produto.setDescricao("Pc bem novo ");
            produto.setPreco(3000.00);
            produto.setQuantidadeEstoque(5.00);
            em.getTransaction().begin();
            em.persist(produto);
            em.getTransaction().commit();

        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
            
        }

        Assert.assertEquals(false, exception);

    }

}
