package br.edu.fema.sacheti.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Session;

import br.edu.fema.sacheti.model.Cliente;

public class ClienteDao {
	@Inject
	private EntityManager em;
	
	public void cadastrar(Cliente cliente){
		em.persist(cliente);
	}
	
	public void alterar(Cliente cliente){
		Session session = em.unwrap(Session.class);
		session.update(cliente);
	}
	
	public void refresh(Cliente cliente){
		em.refresh(cliente);
	}
	
	public Cliente getClienteFromLogin(String login, String senha){
		return em.createQuery("select cli from Cliente cli where cli.usuario.login = :login and cli.usuario.senha = :senha", Cliente.class)
				 .setParameter("login", login)
				 .setParameter("senha", senha)
				 .getSingleResult();
	}

	public List<Cliente> listarTodos() {
		return em.createQuery("select cliente from Cliente cliente", Cliente.class).getResultList();
	}

	public void flush() {
		em.flush();
		
	}
}
