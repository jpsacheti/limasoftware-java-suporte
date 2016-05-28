package br.edu.fema.sacheti.controllers;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.edu.fema.sacheti.dao.ClienteDao;
import br.edu.fema.sacheti.dao.TicketDao;
import br.edu.fema.sacheti.dao.UsuarioDao;
import br.edu.fema.sacheti.intercept.UsuarioInfo;
import br.edu.fema.sacheti.model.Cliente;
import br.edu.fema.sacheti.model.Usuario;

public class ClienteController {
	private final Validator validator;
	private final Result result;
	private final UsuarioDao usuarioDao;
	private final UsuarioInfo usuarioInfo;
	private final TicketDao ticketDao;
	private final ClienteDao clienteDao;
	private Cliente cliente;

	@Inject
	public ClienteController(Validator validator, Result result, UsuarioDao usuarioDao, UsuarioInfo usuarioInfo,
			TicketDao ticketDao, ClienteDao clienteDao) {
		this.validator = validator;
		this.result = result;
		this.usuarioDao = usuarioDao;
		this.usuarioInfo = usuarioInfo;
		this.ticketDao = ticketDao;
		this.clienteDao = clienteDao;
	}

	@PostConstruct
	private void getUsuarioFromCliente() {
		Usuario usr = usuarioInfo.getUsuario();
		if (usr != null) {
			cliente = clienteDao.getClienteFromUsuario(usr);
		}
	}

	/**
	 * @deprecated CDI eyes only
	 */
	public ClienteController() {
		this(null, null, null, null, null, null);
	}

	@Get("/")
	public void home() {
		validator.onErrorRedirectTo(HomeController.class).index();
	}

	@Path("/tickets/abertos")
	@Post
	public void ticketsAbertos() {
		result.include(ticketDao.buscarTicketsAbertosCliente(cliente));
	}
}
