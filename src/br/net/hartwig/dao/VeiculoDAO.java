package br.net.hartwig.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.net.hartwig.model.Veiculo;

/**  
 * HSGV 2017
 * Author: Diego Michel Hartwig
 */
public class VeiculoDAO extends DAO {
	
	//M�todo para salvar 
	public void Salvar(Veiculo veiculo){		
		EntityManager em = getEntityManager().createEntityManager();		
		try{			
			em.getTransaction().begin();			
			em.persist(veiculo);			
			em.getTransaction().commit();			
			em.close();
			
		} catch(Exception ex){
			
			em.getTransaction().rollback();
		}
		
	}
	
	//Retorna um veiculo atrav�s do Id
	public Veiculo Get(int veiculo_id){		
		EntityManager em = getEntityManager().createEntityManager();			
		return em.find(Veiculo.class, veiculo_id);			
	}
	
	//M�todo para atualizar as informa��es
		public void Update(Veiculo veiculo){			
			EntityManager em = getEntityManager().createEntityManager();				
			try{				
				em.getTransaction().begin();
				Veiculo v = em.find(Veiculo.class, veiculo.getId());				
				v.setPlaca(veiculo.getPlaca());		
				v.setKmAtual(veiculo.getKmAtual());	
				em.getTransaction().commit();				
				em.close();
					
			} catch(Exception ex){				
				em.getTransaction().rollback();
			}
				
		}	
		
		//M�todo para deletar 
		public void Delete(Veiculo veiculo){			
			EntityManager em = getEntityManager().createEntityManager();				
			try{				
				em.getTransaction().begin();				
				Veiculo v = em.find(Veiculo.class, veiculo.getId());							
				em.remove(v);				
				em.getTransaction().commit();				
				em.close();					
			} catch(Exception ex){				
				em.getTransaction().rollback();
			}
				
		}
		
		//M�todo que retorna uma lista de ve�culos
		@SuppressWarnings("unchecked")
		public List<Veiculo> GetALL(){			
			EntityManager em = getEntityManager().createEntityManager();			
			List<Veiculo> lista = null;			
			try{				
				Query q = em.createQuery("select object(v) from Veiculo as v");				
				lista = q.getResultList();
				
			} catch(Exception ex){				
				em.close();
			}			
			return lista;
		}	
		

		
	}
