/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.testes.junit;

import br.com.modeljpa.jpa.EntityManagerUtil;
import br.com.modeljpa.modelo.PessoaJuridica;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 
 */
public class TestePersistirPessoaJuridica {
    EntityManager em;
    public TestePersistirPessoaJuridica() {
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
        
        try{
            PessoaJuridica pj = new PessoaJuridica();
            pj.setNome("DevBug");
            pj.setEmail("devbug@gmail.com");
            pj.setTelefone("999254566");
            pj.setCNPJ("48914308000179");
            pj.setIe("52674012321");
            em.getTransaction().begin();
            em.persist(pj);
            em.getTransaction().commit();
            
        }catch(Exception e){
            exception = true;
            e.printStackTrace();
            
        }
        
        Assert.assertEquals(false, exception);
        
    }
    
}
