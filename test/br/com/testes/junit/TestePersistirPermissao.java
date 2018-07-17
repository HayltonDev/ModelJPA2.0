package br.com.testes.junit;

import br.com.modeljpa.jpa.EntityManagerUtil;
import br.com.modeljpa.modelo.Permissao;
import javax.persistence.EntityManager;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestePersistirPermissao {
    EntityManager em;
    public TestePersistirPermissao() {
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
            Permissao permissao = new Permissao();
            permissao.setNome("Teste");
            permissao.setDescricao("Teste do sistema");
            em.getTransaction().begin();
            em.persist(permissao);
            em.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            exception = true;
            
        }
        
        Assert.assertEquals(false, exception);
    }
    
}
