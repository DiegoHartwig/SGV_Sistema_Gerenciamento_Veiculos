package br.net.hartwig.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.net.hartwig.model.Manutencao;

/**  
 * SGSD 2017
 * Author: Diego Michel Hartwig
 */
public class ManutencaoDAO extends DAO {
	// M�todo Salvar Manutencao
	public void Salvar(Manutencao manutencao) {		
		EntityManager em = getEntityManager().createEntityManager();
		try {
			
			em.getTransaction().begin();			
			em.persist(manutencao);			
			em.getTransaction().commit();			
			em.close();
		} catch (Exception ex) {			
			em.getTransaction().rollback();
		}
	}

	// M�todo que retorna uma manuten��o
	public Manutencao Get(int manutencao_id) {		
		EntityManager em = getEntityManager().createEntityManager();		
		return em.find(Manutencao.class, manutencao_id);

	}	

	// M�todo para listar todas as manuten��es
	@SuppressWarnings("unchecked")
	public List<Manutencao> GetALL() {		
		EntityManager em = getEntityManager().createEntityManager();		
		List<Manutencao> lista = null;

		try {			
			Query q = em.createQuery("select object(m) from Manutencao as m order by c.dataManutencao DESC ");
			
			lista = q.getResultList();

		} catch (Exception ex) {
			
			em.close();
		}		
		return lista;
	}

}
