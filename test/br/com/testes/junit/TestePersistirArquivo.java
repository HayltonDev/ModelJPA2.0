/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.testes.junit;

import br.com.modeljpa.jpa.EntityManagerUtil;
import br.com.modeljpa.modelo.Arquivo;
import br.com.modeljpa.modelo.Produto;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class TestePersistirArquivo {

    EntityManager em;

    public TestePersistirArquivo() {
    }

    @Before
    public void setUp() {
        System.out.println("Entoru no setUp");
        em = EntityManagerUtil.getEntityManager();
    }

    @After
    public void tearDown() {
        System.out.println("Entrou em tearDown e fechou em");
        em.close();
    }

    @Test
    public void teste() {
        boolean exception = false;
        try {
            System.out.println("Entrou no teste");
            Arquivo arquivo = new Arquivo();
            Produto produto = em.getReference(Produto.class, 2);
            arquivo.setNome("document.pdf");
            arquivo.setDescricao("Descreve tudo sobre o smartphone");
            Path path = Paths.get("C:\\Users\\T1076986\\Desktop\\" + arquivo.getNome());
            arquivo.setArquivo(Files.readAllBytes(path));
            produto.adicionarArquivo(arquivo);
            
            em.getTransaction().begin();
            em.persist(arquivo);
            em.getTransaction().commit();
        } catch (IOException e) {
            exception = true;
            Logger.getLogger(TestePersistirArquivo.class.getName()).log(Level.SEVERE, null, e);
        }
        
        Assert.assertEquals(false, exception);

    }

}
