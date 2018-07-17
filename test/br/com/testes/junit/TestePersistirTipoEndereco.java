package br.com.testes.junit;

import br.com.modeljpa.jpa.EntityManagerUtil;
import br.com.modeljpa.modelo.TipoEndereco;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Haylton
 */
public class TestePersistirTipoEndereco {
    EntityManager em;
    
    public TestePersistirTipoEndereco() {
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
            TipoEndereco tipoEndereco = new TipoEndereco();
            tipoEndereco.setDescricao("Apartamento");
            em.getTransaction().begin();
            em.persist(tipoEndereco);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
            
        }
        
        Assert.assertEquals(false, exception);
    }
    
}
