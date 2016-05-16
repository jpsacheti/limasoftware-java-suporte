package br.edu.fema.sacheti.dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.fema.sacheti.model.Ticket;

public class TicketDao {
	@Inject
	private EntityManager session;
	
	public void cadastrar(Ticket ticket){
		session.persist(ticket);
	}
}
