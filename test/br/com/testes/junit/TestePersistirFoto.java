package br.com.testes.junit;

import br.com.modeljpa.jpa.EntityManagerUtil;
import br.com.modeljpa.modelo.Foto;
import br.com.modeljpa.modelo.Produto;
import java.io.IOException;
import java.nio.file.FileSystems;
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

public class TestePersistirFoto {

    EntityManager em;

    public TestePersistirFoto() {
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
    public void teste() {
        boolean exception = false;
        try {
            Produto produto = em.getReference(Produto.class, 2);
            Foto foto = new Foto();
            foto.setNome("galaxy-s9.jpg");
            foto.setDescricao("Produto novo");
            Path path = Paths.get("C:\\Desenvolvimento\\Java\\Libs\\" + foto.getNome());
            foto.setArquivo(Files.readAllBytes(path));  
            produto.adicionarFoto(foto);
            em.getTransaction().begin();
            em.persist(foto);
            em.getTransaction().commit();
        }catch (IOException ex) {
            exception = true;
            Logger.getLogger(TestePersistirFoto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Assert.assertEquals(false, exception);
    }

}
