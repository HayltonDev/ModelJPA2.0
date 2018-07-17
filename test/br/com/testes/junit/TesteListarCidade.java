package br.com.testes.junit;

import br.com.modeljpa.jpa.EntityManagerUtil;
import br.com.modeljpa.modelo.Cidade;
import br.com.modeljpa.modelo.Estado;
import java.util.List;
import javax.persistence.EntityManager;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 
 */
public class TesteListarCidade {
    EntityManager em;
    public TesteListarCidade() {
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
            String jpql = "from Cidade order by nome";
            List<Cidade> lista = em.createQuery(jpql).getResultList();
            for(Cidade c : lista){
                System.out.println("ID: "+c.getId() + " Nome: " + c.getNome() + " Estado: "+c.getEstado().getNome());
            }
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
        }
        Assert.assertEquals(false,exception);
    }
    
}
