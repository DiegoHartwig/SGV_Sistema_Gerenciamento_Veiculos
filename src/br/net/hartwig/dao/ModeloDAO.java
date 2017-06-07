package br.net.hartwig.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.net.hartwig.model.Modelo;

/**  
 * HSGV 2017
 * Author: Diego Michel Hartwig
 */
public class ModeloDAO extends DAO {
	
	//Método para salvar 
	public void Salvar(Modelo modelo){		
		EntityManager em = getEntityManager().createEntityManager();		
		try{			
			em.getTransaction().begin();			
			em.persist(modelo);			
			em.getTransaction().commit();			
			em.close();
			
		} catch(Exception ex){
			
			em.getTransaction().rollback();
		}
		
	}
	
	//Retorna um modelo através do Id
	public Modelo Get(int modelo_id){		
		EntityManager em = getEntityManager().createEntityManager();			
		return em.find(Modelo.class, modelo_id);			
	}
	
	//Método para atualizar as informações
		public void Update(Modelo modelo){			
			EntityManager em = getEntityManager().createEntityManager();				
			try{				
				em.getTransaction().begin();
				Modelo m = em.find(Modelo.class, modelo.getId());				
				m.setNome(modelo.getNome());															
				em.getTransaction().commit();				
				em.close();
					
			} catch(Exception ex){				
				em.getTransaction().rollback();
			}
				
		}	
		
		//Método para deletar 
		public void Delete(Modelo modelo){			
			EntityManager em = getEntityManager().createEntityManager();				
			try{				
				em.getTransaction().begin();				
				Modelo m = em.find(Modelo.class, modelo.getId());							
				em.remove(m);				
				em.getTransaction().commit();				
				em.close();					
			} catch(Exception ex){				
				em.getTransaction().rollback();
			}
				
		}
		
		//Método que retorna uma lista de modelos
		@SuppressWarnings("unchecked")
		public List<Modelo> GetALL(){			
			EntityManager em = getEntityManager().createEntityManager();			
			List<Modelo> lista = null;			
			try{				
				Query q = em.createQuery("select object(m) from Modelo as m");				
				lista = q.getResultList();
				
			} catch(Exception ex){				
				em.close();
			}			
			return lista;
		}	
		

		
	}
