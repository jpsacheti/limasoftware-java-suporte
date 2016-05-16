package br.edu.fema.sacheti.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.fema.sacheti.model.Usuario;

public class UsuarioDao {
	@Inject
	private EntityManager em;
	
	public void refresh(Usuario usuario) throws Exception{
		em.refresh(usuario);
	}
}
