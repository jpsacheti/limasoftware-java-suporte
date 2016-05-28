package br.edu.fema.sacheti.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.fema.sacheti.model.Cliente;
import br.edu.fema.sacheti.model.Usuario;

public class ClienteDao {
	@Inject
	private EntityManager em;
	
	public Cliente getClienteFromUsuario(Usuario usuario){
		return em.createQuery("select c from Cliente c where c.usuario = :usuario", Cliente.class)
				.setParameter("usuario", usuario)
				.getSingleResult();
	}
}
