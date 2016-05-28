package br.edu.fema.sacheti.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
	
	public Usuario pesquisar(String login, String senha){
		try{
			return em.createQuery("select from Usuario u where u.login = :login and u.senha = :senha", Usuario.class)
					.setParameter("login", login)
					.setParameter("senha", senha)
					.getSingleResult();
		} catch(NoResultException e){
			return null;
		}
	}
	
	public boolean existeLogin(String login){
	    try {
			em.createQuery("select from Usuario u where u.login = :login", Usuario.class).getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}
	
	public void atualizar(Usuario usuario){
		em.merge(usuario);
	}
}
