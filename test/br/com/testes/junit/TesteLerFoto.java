package br.com.testes.junit;

import br.com.modeljpa.jpa.EntityManagerUtil;
import br.com.modeljpa.modelo.Foto;
import java.io.File;
import java.io.FileOutputStream;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TesteLerFoto {
    EntityManager em;
    
    public TesteLerFoto() {
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
            Foto foto = em.getReference(Foto.class, 1);
            File file = new File("C:\\Desenvolvimento\\Java\\Libs\\galaxy-s9.jpg");
            FileOutputStream out = new FileOutputStream(file);
            out.write(foto.getArquivo());
            out.close();
                        
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
            
        }
        
        Assert.assertEquals(false, exception);
        
    }
}
