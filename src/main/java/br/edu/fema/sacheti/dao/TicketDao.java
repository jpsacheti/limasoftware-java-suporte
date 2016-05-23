package br.edu.fema.sacheti.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.fema.sacheti.model.Ticket;

public class TicketDao {
	@Inject
	private EntityManager session;
	
	public void cadastrar(Ticket ticket){
		session.persist(ticket);
	}
	
	public void alterar(Ticket ticket){
		session.merge(ticket);
	}
	
	public void excluir(Ticket ticket){
		session.remove(ticket);
	}
	
	public List<Ticket> buscarTodos(){
		return session.createQuery("select t from Ticket t", Ticket.class).getResultList();
	}
}
