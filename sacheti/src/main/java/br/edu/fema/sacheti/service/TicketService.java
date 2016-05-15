package br.edu.fema.sacheti.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.fema.sacheti.model.Ticket;

public class TicketService {
	@Inject
	private EntityManager session;
	
	public void cadastrar(Ticket ticket){
		session.persist(ticket);
	}
}
