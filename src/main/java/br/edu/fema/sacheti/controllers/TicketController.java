package br.edu.fema.sacheti.controllers;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.edu.fema.sacheti.dao.TicketDao;
import br.edu.fema.sacheti.intercept.Admin;
import br.edu.fema.sacheti.intercept.ClienteInfo;
import br.edu.fema.sacheti.intercept.OperadorInfo;
import br.edu.fema.sacheti.model.Interacao;
import br.edu.fema.sacheti.model.Ticket;

@Controller
public class TicketController {

	private final TicketDao ticketDao;
	private final Result result;
	private final OperadorInfo operadorInfo;
	private final ClienteInfo clienteInfo;
	private final Validator validator;

	/**
	 * @deprecated CDI eyes only
	 */
	public TicketController() {
		this(null, null, null, null, null);
	}

	@Inject
	public TicketController(TicketDao ticketDao, Result result, OperadorInfo operadorInfo, Validator validator, ClienteInfo clienteInfo) {
		this.ticketDao = ticketDao;
		this.result = result;
		this.operadorInfo = operadorInfo;
		this.validator = validator;
		this.clienteInfo = clienteInfo;
	}

	@Admin
	@Post
	public void ticketsResolver() {
		result.include("tickets", ticketDao.buscarTodos());
	}
	
	@Path("/tickets/abertos")
	@Post
	public void ticketsAbertosCliente() {
		result.include(ticketDao.buscarTicketsAbertosCliente(clienteInfo.getCliente()));
	}

	
	@Post 
	public void visualizarTicket(Ticket ticket) {
		result.include("ticket", ticketDao.pesquisarTicket(ticket));
	}
	
	@Admin
	@Post
	public void responderTicket(Ticket ticket){
		Interacao interacao = new Interacao();
		interacao.setTicket(ticket);
		interacao.setUsuario(operadorInfo.getUsuario());
		interacao.setDataInteracao(LocalDate.now());
		result.include("ticket", ticket);
		result.include("interacao", interacao);
	}
	
	public void finalizarResposta(Ticket ticket, @Valid Interacao interacao){
		validator.onErrorRedirectTo(this).finalizarResposta(ticket, interacao);
		ticket.getInteracoes().add(interacao);
		ticketDao.atualizar(ticket);
		result.redirectTo(this).ticketsResolver();
	}
}
