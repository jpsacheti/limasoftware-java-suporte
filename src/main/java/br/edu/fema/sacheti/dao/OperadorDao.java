package br.edu.fema.sacheti.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.fema.sacheti.model.Operador;

public class OperadorDao {
	@Inject
	private EntityManager em;

	public void refresh(Operador operador) {
		em.refresh(operador);
	}

	public Operador getOperadorFromLogin(String login, String senha) {
		return em
				.createQuery("select op from Operador op where op.usuario.login = :login and op.usuario.senha = :senha",
						Operador.class)
				.setParameter("login", login)
				.setParameter("senha", senha)
				.getSingleResult();
	}
}
