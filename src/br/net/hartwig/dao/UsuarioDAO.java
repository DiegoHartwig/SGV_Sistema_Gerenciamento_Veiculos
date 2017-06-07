package br.net.hartwig.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.net.hartwig.model.Usuario;

/**  
 * HSGV 2017
 * Author: Diego Michel Hartwig
 */
public class UsuarioDAO extends DAO {
	
	//M�todo para salvar um Usu�rio
	public void Salvar(Usuario usuario){		
		EntityManager em = getEntityManager().createEntityManager();		
		try{			
			em.getTransaction().begin();			
			em.persist(usuario);			
			em.getTransaction().commit();			
			em.close();
			
		} catch(Exception ex){
			
			em.getTransaction().rollback();
		}
		
	}
	
	//Retorna um tecnico atrav�s do Id
	public Usuario Get(int usuario_id){		
		EntityManager em = getEntityManager().createEntityManager();			
		return em.find(Usuario.class, usuario_id);			
	}
	
	//M�todo para atualizar as informa��es de um usu�rio
		public void Update(Usuario usuario){			
			EntityManager em = getEntityManager().createEntityManager();				
			try{				
				em.getTransaction().begin();
				Usuario u = em.find(Usuario.class, usuario.getId());				
				u.setNome(usuario.getNome());
				u.setLogin(usuario.getLogin());
				u.setSenha(usuario.getSenha());
				u.setEmail(usuario.getEmail());
				u.setTelefone(usuario.getTelefone());				
				u.setObs(usuario.getObs());								
				em.getTransaction().commit();				
				em.close();
					
			} catch(Exception ex){				
				em.getTransaction().rollback();
			}
				
		}	
		
		//M�todo para deletar 
		public void Delete(Usuario usuario){			
			EntityManager em = getEntityManager().createEntityManager();				
			try{				
				em.getTransaction().begin();				
				Usuario u = em.find(Usuario.class, usuario.getId());							
				em.remove(u);				
				em.getTransaction().commit();				
				em.close();					
			} catch(Exception ex){				
				em.getTransaction().rollback();
			}
				
		}
		
		//M�todo que retorna uma lista de usu�rios
		@SuppressWarnings("unchecked")
		public List<Usuario> GetALL(){			
			EntityManager em = getEntityManager().createEntityManager();			
			List<Usuario> lista = null;			
			try{				
				Query q = em.createQuery("select object(u) from Usuario as u");				
				lista = q.getResultList();
				
			} catch(Exception ex){				
				em.close();
			}			
			return lista;
		}	
		
		//M�todo de autentica��o 
		public Usuario getAutenticaUsuario(String login, String senha){
			EntityManager em = getEntityManager().createEntityManager();
			try{
				Usuario usuario = (Usuario)em.createQuery("select u from Usuario u where u.login = :login and u.senha = :senha")
											.setParameter("login", login)
											.setParameter("senha", senha).getSingleResult();
				
				return usuario;
				
			} catch (NoResultException e) {
				return null;
						
						
			}
		}

		
	}


