package br.com.testes.junit;

import br.com.modeljpa.jpa.EntityManagerUtil;
import br.com.modeljpa.modelo.Compra;
import br.com.modeljpa.modelo.CompraID;
import br.com.modeljpa.modelo.CompraItem;
import br.com.modeljpa.modelo.PessoaJuridica;
import br.com.modeljpa.modelo.Produto;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestePersistirCompra {
    EntityManager em;
    public TestePersistirCompra() {
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
    public void teste(){
        boolean exception = false;
        try {
            Compra compra = new Compra();
            Produto produto = em.getReference(Produto.class, 3);
            PessoaJuridica pessoaJuridica = em.getReference(PessoaJuridica.class, 2);
            CompraID compraID = new CompraID();
            compraID.setPessoaJuridica(pessoaJuridica);
            compraID.setNumeroNota(123456);
            CompraItem compraItem = new CompraItem();
            compraItem.setProduto(produto);
            compraItem.setQuantidade(3.00);
            compraItem.setValorUnitario(produto.getPreco());
            compraItem.setValorTotal(compraItem.getValorUnitario() * compraItem.getQuantidade());
            compra.setDataCompra(Calendar.getInstance());
            compra.setValorTotal(compraItem.getValorUnitario() * compraItem.getQuantidade());
            compra.setCompraID(compraID);
            compra.adionarItem(compraItem);
            
            
            em.getTransaction().begin();
            em.persist(compra);
            em.getTransaction().commit();
            
        } catch (Exception e) {
            exception = true;
            Logger.getLogger(TestePersistirCompra.class.getSimpleName()).log(Level.SEVERE,null, e);
            
        }
        Assert.assertEquals(false, exception);
        
    }
    
}
