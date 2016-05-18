package br.edu.fema.sacheti.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import br.edu.fema.sacheti.model.Usuario;

public class UsuarioDao {
	@Inject
	private EntityManager em;

	public void refresh(Usuario usuario) throws Exception {
		em.refresh(usuario);
	}

	public void cadastrar(Usuario usuario) {
		em.persist(usuario);
	}

	public List<Usuario> pesquisarTodos() {
		CriteriaQuery<Usuario> criteria = em.getCriteriaBuilder().createQuery(Usuario.class);
		TypedQuery<Usuario> tq = em.createQuery(criteria.select(criteria.from(Usuario.class)));
		return tq.getResultList();
	}
}
