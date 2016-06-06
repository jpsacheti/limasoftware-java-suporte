package br.edu.fema.sacheti.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.edu.fema.sacheti.model.Cliente;
import br.edu.fema.sacheti.model.Ticket;

public class TicketDao {
	@Inject
	private EntityManager session;
	
	public void cadastrar(Ticket ticket){
		session.persist(ticket);
	}
	
	public void excluir(Ticket ticket){
		session.remove(ticket);
	}
	
	public List<Ticket> buscarTodos(){
		return session.createQuery("select t from Ticket t", Ticket.class).getResultList();
	}
	
	public List<Ticket> buscarTicketsCliente(Cliente cliente){
		return session.createQuery("select t from Ticket t where t.cliente = :cliente", Ticket.class)
				.setParameter("cliente", cliente)
				.getResultList();
	}
	
	public List<Ticket> buscarTicketsAbertosCliente(Cliente cliente){
		return session.createQuery("select t from Ticket t where t.cliente = :cliente and t.finalizado = false", Ticket.class)
				.setParameter("cliente",cliente)
				.getResultList();
	}
	
	public List<Ticket> buscartTodosTicketsAtivos(){
		return session.createQuery("select t from Ticket t where t.finalizado = false", Ticket.class)
				.getResultList();
	}

	public Ticket pesquisarTicket(Ticket ticket) {
		return session.find(Ticket.class, ticket.getCodigo());
	}

	public void atualizar(Ticket ticket) {
		session.merge(ticket);
	}
}
