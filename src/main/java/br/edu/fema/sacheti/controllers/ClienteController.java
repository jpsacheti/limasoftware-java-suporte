package br.edu.fema.sacheti.controllers;

import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.edu.fema.sacheti.dao.ClienteDao;
import br.edu.fema.sacheti.dao.TicketDao;
import br.edu.fema.sacheti.intercept.ClienteInfo;
import br.edu.fema.sacheti.intercept.Publico;
import br.edu.fema.sacheti.model.Cliente;

@Controller
public class ClienteController {
	private final Validator validator;
	private final Result result;
	private final ClienteInfo clienteInfo;
	private final TicketDao ticketDao;
	private final ClienteDao clienteDao;

	@Inject
	public ClienteController(Validator validator, Result result, ClienteInfo clienteInfo, TicketDao ticketDao,
			ClienteDao clienteDao) {
		this.validator = validator;
		this.result = result;
		this.clienteInfo = clienteInfo;
		this.ticketDao = ticketDao;
		this.clienteDao = clienteDao;
	}

	/**
	 * @deprecated CDI eyes only
	 */
	public ClienteController() {
		this(null, null, null, null, null);
	}

	@Get
	public void home() {
		validator.onErrorRedirectTo(HomeController.class).index();
	}

	@Path("/tickets/abertos")
	@Post
	public void ticketsAbertos() {
		result.include(ticketDao.buscarTicketsAbertosCliente(clienteInfo.getCliente()));
	}

	@Post
	@Publico
	public void login(String login, String senha) {
		validator.onErrorForwardTo(HomeController.class).index();
		clienteInfo.login(clienteDao.getClienteFromLogin(login, senha));
		result.forwardTo(this).ticketsAbertos();
	}
	
	@Consumes("application/xml")
	@Path("/clientes/cadastrar")
	public void cadastrarCliente(Cliente cliente){
		clienteDao.cadastrar(cliente);
	}
	
	public void listarXml(){
		result.use(Results.xml()).from(clienteDao.listarTodos()).serialize();
	}
}
