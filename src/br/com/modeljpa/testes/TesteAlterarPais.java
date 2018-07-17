package br.com.modeljpa.testes;

import br.com.modeljpa.modelo.Pais;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Haylton
 */
public class TesteAlterarPais {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IFSULModelPU");
        EntityManager em = emf.createEntityManager();
        Pais p = em.find(Pais.class, 2); //isso é para alterar, no momento de criação do objeto eu chamo o método find da EM, passo que classe eu quero alterar e o identificador no bd
        p.setNome("Estados Unidos"); //estou settando aqui para alterar o registro 2 que antes era argentina
        p.setIso("EUA");
        em.getTransaction().begin();
        em.merge(p); //é responsável por juntar o estado persistente para o estado transiente que é o objeto que está e memória, ou seja, traz o pais(select) e faz o update
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
