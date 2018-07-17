package br.com.testes.junit;

import br.com.modeljpa.jpa.EntityManagerUtil;
import br.com.modeljpa.modelo.Arquivo;
import java.io.File;
import java.io.FileOutputStream;
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
public class TesteLerArquivo {
    EntityManager em;
    public TesteLerArquivo() {
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
            Arquivo arquivo = em.getReference(Arquivo.class, 1);
            File file = new File("C:\\Users\\T1076986\\Desktop\\" + arquivo.getNome());
            FileOutputStream out = new FileOutputStream(file);
            out.write(arquivo.getArquivo());
            out.close();
        } catch (Exception e) {
            exception=true;
            Logger.getLogger(TesteLerArquivo.class.getSimpleName()).log(Level.SEVERE, null, e);
        }
        
        Assert.assertEquals(false, exception);
    }
}
