
package br.com.modeljpa.testes;

import br.com.modeljpa.modelo.Pais;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 *
 * @author Haylton
 */
public class TesteValidarPais {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IFSULModelPU");
        EntityManager em = emf.createEntityManager();
        Pais p = new Pais(); 
        p.setNome("Colombia");
        p.setIso("COL");
        
        em.getTransaction().begin();
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Pais>> erros = validador.validate(p);
        if(erros.size() > 0){
            for(ConstraintViolation<Pais> erro : erros){
                System.out.println("Erro: "+erro.getMessage());
            }
        }else{
                  em.persist(p);   
        }
       
        em.getTransaction().commit();
        em.close();
        emf.close();
        
        
    }
}
