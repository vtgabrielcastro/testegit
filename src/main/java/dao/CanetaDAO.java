package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Caneta;
import util.JPAutil;

public class CanetaDAO {
	
	 public static void salvar(Caneta caneta) {
	        EntityManager em = JPAutil.criarEntityManager();
	        em.getTransaction().begin();
	        em.persist(caneta);  
	        
	        em.getTransaction().commit();
	        em.close();
	    }
	 
	 public static void excluir(Caneta caneta) {
	        EntityManager em = JPAutil.criarEntityManager();
	        em.getTransaction().begin();

	        caneta = em.find(Caneta.class, caneta.getId()); //passa para o estado gerenciado
	        em.remove(caneta); //só consegue remover no estado gerenciado	    
	        
	        em.getTransaction().commit();
	        em.close();
	    }
	 
	 public static List<Caneta> listar() {
	        EntityManager em = JPAutil.criarEntityManager();
	        Query q = em.createQuery("select c from Caneta c");
	        @SuppressWarnings("unchecked")
			List<Caneta> resultado = q.getResultList();
	        em.close();
	        return resultado;
	    }
}
