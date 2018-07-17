package br.com.modeljpa.testes;

import br.com.modeljpa.modelo.Pais;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Haylton
 */
public class TesteExcluirPais {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IFSULModelPU");
        EntityManager em = emf.createEntityManager();
        Pais p = em.find(Pais.class, 3); //nesse caso eu vou excluir, no momento de criação do objeto eu chamo o método find da EM, passo que classe eu quero exlcuir e o identificador no bd
        p.setNome("Estados Unidos"); //estou settando aqui para alterar o registro 2 que antes era argentina
        p.setIso("EUA");
        em.getTransaction().begin();
        em.remove(p); //é responsável por excluir o pais 3 no bd que passei no find
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
