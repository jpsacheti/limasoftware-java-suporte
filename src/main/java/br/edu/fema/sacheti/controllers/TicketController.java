package br.edu.fema.sacheti.controllers;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.edu.fema.sacheti.dao.TicketDao;
import br.edu.fema.sacheti.intercept.Admin;
import br.edu.fema.sacheti.intercept.OperadorInfo;
import br.edu.fema.sacheti.model.Ticket;

@Controller
public class TicketController {

	private final TicketDao ticketDao;
	private final Result result;
	private final OperadorInfo operadorInfo;

	/**
	 * @deprecated CDI eyes only
	 */
	public TicketController() {
		this(null, null, null);
	}

	@Inject
	public TicketController(TicketDao ticketDao, Result result, OperadorInfo operadorInfo) {
		this.ticketDao = ticketDao;
		this.result = result;
		this.operadorInfo = operadorInfo;
	}

	@Admin
	@Post
	public void ticketsAbertos() {
		result.include("tickets", ticketDao.buscarTodos());
	}
	
	@Post public void visualizarTicket(Ticket ticket) {
		result.include("ticket", ticketDao.pesquisarTicket(ticket));
	}
	
	@Admin
	@Post
	public void responderTicket(Ticket ticket){
		result.include("ticket", ticket);
		//result.re
		//TODO: continuar daqui
	}
}
