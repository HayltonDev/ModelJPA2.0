package br.com.testes.junit;

import br.com.modeljpa.jpa.EntityManagerUtil;
import br.com.modeljpa.modelo.PessoaFisica;
import br.com.modeljpa.modelo.Produto;
import br.com.modeljpa.modelo.Venda;
import br.com.modeljpa.modelo.VendaItem;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.theories.suppliers.TestedOn;

/**
 *
 * @author Haylton
 */
public class TestePersistirVenda {
    EntityManager em;
    public TestePersistirVenda() {
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
            Produto produto = em.getReference(Produto.class, 2);
            PessoaFisica pessoaFisica = em.getReference(PessoaFisica.class, 1);
            Venda venda = new Venda();
            venda.setDataVenda(Calendar.getInstance());
            venda.setParcelas(3);
            venda.setPessoaFisica(pessoaFisica);
             VendaItem vendaItem = new VendaItem();
             vendaItem.setProduto(produto);
             vendaItem.setQuantidade(5.00);
             vendaItem.setValorUnitario(produto.getPreco());
             vendaItem.setValorTotal(vendaItem.getQuantidade() * vendaItem.getValorUnitario());
             venda.adicionarItem(vendaItem);
             venda.gerarParcelas();
             em.getTransaction().begin();
             em.persist(venda);
             em.getTransaction().commit();
            
        } catch (Exception e) {
            exception = true;
            Logger.getLogger(TestePersistirVenda.class.getSimpleName()).log(Level.SEVERE, null, e);
        }
        
        Assert.assertEquals(false, exception);
    }
    
}
