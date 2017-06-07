package br.net.hartwig.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.net.hartwig.model.Componente;


/**  
 * HSGV 2017
 * Author: Diego Michel Hartwig
 */
public class ComponenteDAO extends DAO {
	
	//Método para salvar 
	public void Salvar(Componente componente){		
		EntityManager em = getEntityManager().createEntityManager();		
		try{			
			em.getTransaction().begin();			
			em.persist(componente);			
			em.getTransaction().commit();			
			em.close();
			
		} catch(Exception ex){
			
			em.getTransaction().rollback();
		}
		
	}
	
	//Retorna um componente pelo id
	public Componente Get(int componente_id){		
		EntityManager em = getEntityManager().createEntityManager();			
		return em.find(Componente.class, componente_id);			
	}
	
	//Método para atualizar as informações
		public void Update(Componente componente){			
			EntityManager em = getEntityManager().createEntityManager();				
			try{				
				em.getTransaction().begin();
				Componente m = em.find(Componente.class, componente.getId());				
				m.setNome(componente.getNome());															
				em.getTransaction().commit();				
				em.close();
					
			} catch(Exception ex){				
				em.getTransaction().rollback();
			}
				
		}	
		
		//Método para deletar 
		public void Delete(Componente componente){			
			EntityManager em = getEntityManager().createEntityManager();				
			try{				
				em.getTransaction().begin();				
				Componente m = em.find(Componente.class, componente.getId());							
				em.remove(m);				
				em.getTransaction().commit();				
				em.close();					
			} catch(Exception ex){				
				em.getTransaction().rollback();
			}
				
		}
		
		//Método que retorna uma lista de componentes
		@SuppressWarnings("unchecked")
		public List<Componente> GetALL(){			
			EntityManager em = getEntityManager().createEntityManager();			
			List<Componente> lista = null;			
			try{				
				Query q = em.createQuery("select object(c) from Componente as c");				
				lista = q.getResultList();
				
			} catch(Exception ex){				
				em.close();
			}			
			return lista;
		}	
		

		
	}
