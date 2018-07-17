
package br.com.modeljpa.testes;

import br.com.modeljpa.jpa.EntityManagerUtil;
import br.com.modeljpa.modelo.Pais;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Haylton
 */
public class TestePersistirPais {
    public static void main(String[] args) {
        EntityManager em = EntityManagerUtil.getEntityManager(); 
        Pais p = new Pais(); 
        p.setNome("Canadá");
        p.setIso("CAN");
        
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
        
        
    }
}
