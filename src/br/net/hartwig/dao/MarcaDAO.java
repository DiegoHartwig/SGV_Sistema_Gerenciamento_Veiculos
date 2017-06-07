package br.net.hartwig.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.net.hartwig.model.Marca;

/**  
 * HSGV 2017
 * Author: Diego Michel Hartwig
 */
public class MarcaDAO extends DAO {
	
	//M�todo para salvar 
	public void Salvar(Marca marca){		
		EntityManager em = getEntityManager().createEntityManager();		
		try{			
			em.getTransaction().begin();			
			em.persist(marca);			
			em.getTransaction().commit();			
			em.close();
			
		} catch(Exception ex){
			
			em.getTransaction().rollback();
		}
		
	}
	
	//Retorna uma marca atrav�s do Id
	public Marca Get(int marca_id){		
		EntityManager em = getEntityManager().createEntityManager();			
		return em.find(Marca.class, marca_id);			
	}
	
	//M�todo para atualizar as informa��es
		public void Update(Marca marca){			
			EntityManager em = getEntityManager().createEntityManager();				
			try{				
				em.getTransaction().begin();
				Marca m = em.find(Marca.class, marca.getId());				
				m.setNome(marca.getNome());															
				em.getTransaction().commit();				
				em.close();
					
			} catch(Exception ex){				
				em.getTransaction().rollback();
			}
				
		}	
		
		//M�todo para deletar 
		public void Delete(Marca marca){			
			EntityManager em = getEntityManager().createEntityManager();				
			try{				
				em.getTransaction().begin();				
				Marca m = em.find(Marca.class, marca.getId());							
				em.remove(m);				
				em.getTransaction().commit();				
				em.close();					
			} catch(Exception ex){				
				em.getTransaction().rollback();
			}
				
		}
		
		//M�todo que retorna uma lista de marcas
		@SuppressWarnings("unchecked")
		public List<Marca> GetALL(){			
			EntityManager em = getEntityManager().createEntityManager();			
			List<Marca> lista = null;			
			try{				
				Query q = em.createQuery("select object(m) from Marca as m");				
				lista = q.getResultList();
				
			} catch(Exception ex){				
				em.close();
			}			
			return lista;
		}	
		

		
	}
