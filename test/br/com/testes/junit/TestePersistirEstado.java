/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.testes.junit;

import br.com.modeljpa.jpa.EntityManagerUtil;
import br.com.modeljpa.modelo.Estado;
import br.com.modeljpa.modelo.Pais;
import javax.persistence.EntityManager;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Haylton
 */
public class TestePersistirEstado {
    EntityManager em;
    
    public TestePersistirEstado() {
    }
    
    @Before
    public void setUp() {
        
        em = EntityManagerUtil.getEntityManager();
        
    }
    
    @After
    public void tearDown() {
        em.close();
    }
    
    @Test //seria equivalente ao PSVM
    public void teste(){
        boolean exception = false;
        try{
            Estado e = new Estado();
            e.setNome("Goías"); 
            e.setUf("GO");
            e.setPais(em.find(Pais.class, 1)); //em.find to trazendo do banco, fazendo um select
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
        } catch(Exception e){
            exception = true;
            e.printStackTrace();
        }
        
        Assert.assertEquals(false,exception); //tô verificando com o boolean que eu criei para saber se a transação ocorreu ou não
    }
    
}
