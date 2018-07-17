/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.testes.junit;

import br.com.modeljpa.jpa.EntityManagerUtil;
import br.com.modeljpa.modelo.Estado;
import br.com.modeljpa.modelo.Pais;
import br.com.modeljpa.modelo.PessoaFisica;
import java.util.Calendar;
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
public class TestePersistirPessoaFisica {
    EntityManager em;
    
    public TestePersistirPessoaFisica() {
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
            PessoaFisica pf = new PessoaFisica();
            pf.setNome("Jãozinho silva");
            pf.setEmail("joao@gmail.com");
            pf.setNascimento(Calendar.getInstance());
            pf.setNomeUsuario("jaosil");
            pf.setRg("1234567891");
            pf.setSenha("senha");
            pf.setTelefone("(69)99999999");
            pf.setCpf("70546531067");
            
            em.getTransaction().begin();
            em.persist(pf);
            em.getTransaction().commit();
            
            
        } catch(Exception e){
            exception = true;
            e.printStackTrace();
        }
        
        Assert.assertEquals(false,exception); //tô verificando com o boolean que eu criei para saber se a transação ocorreu ou não
    }
    
}
