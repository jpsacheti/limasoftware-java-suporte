package br.edu.fema.sacheti.controllers;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.edu.fema.sacheti.dao.TicketDao;
import br.edu.fema.sacheti.intercept.Admin;
import br.edu.fema.sacheti.intercept.ClienteInfo;
import br.edu.fema.sacheti.intercept.OperadorInfo;
import br.edu.fema.sacheti.intercept.Publico;
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
	
	@Path("/ticket/abertos")
	@Post
	public void ticketsAbertosCliente() {
		result.include("ticketList",ticketDao.buscarTicketsAbertosCliente(clienteInfo.getCliente()));
		result.of(this).listar();
	}
	
	@Path("/ticket/todos")
	public void todosTicketsCliente(){
		result.include("ticketList", ticketDao.buscarTicketsCliente(clienteInfo.getCliente()));
		result.of(this).listar();
	}

	
	@Post("/ticket/visualizar/{ticket.codigo}") 
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
	
	@Get
	public void finalizarResposta(Ticket ticket, @Valid Interacao interacao){
		validator.onErrorRedirectTo(this).finalizarResposta(ticket, interacao);
		ticket.getInteracoes().add(interacao);
		ticketDao.atualizar(ticket);
		result.redirectTo(this).ticketsResolver();
	}
	
	@Publico
	public void listar(){
		
	}
	
	public void formulario(boolean inclusao){
		result.include(inclusao);	
	}
	
	@Get("/ticket/interacao")
	public void cadastrarInteracao(Ticket ticket){
		result.include(ticket);
	}
	
	@Post
	public void incluirInteracao(Ticket ticket, Interacao interacao){
		interacao.setTicket(ticket);
		interacao.setUsuario(clienteInfo.getUsuario());
		interacao.setDataInteracao(LocalDate.now());
		ticket.getInteracoes().add(interacao);
		ticketDao.atualizar(ticket);
		result.redirectTo(this).ticketsAbertosCliente();
	}
	
	@Delete("/ticket/remover/{codigo}")
	public void remover(Integer codigo){
		Ticket ticket = ticketDao.pesquisarTicket(codigo);
		ticketDao.excluir(ticket);
		result.nothing();
	}
	
	@Get
	public void cadastrar(){
		result.forwardTo(this).formulario(true);
	}
	
	@Get
	public void finalizar(Ticket ticket){
		ticket.setFinalizado(true);
		ticketDao.atualizar(ticket);
		result.include("mensagem", "Ticket finalizado com sucesso!");
		result.redirectTo(this).visualizarTicket(ticket);
	}
}
