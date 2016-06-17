package br.edu.fema.sacheti.controllers;

import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.environment.Property;
import br.com.caelum.vraptor.validator.SimpleMessage;
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
	private final ClienteDao clienteDao;
	@Inject
	@Property("chave.acesso")
	private String token;

	@Inject
	public ClienteController(Validator validator, Result result, ClienteInfo clienteInfo, TicketDao ticketDao, ClienteDao clienteDao) {
		this.validator = validator;
		this.result = result;
		this.clienteInfo = clienteInfo;
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

	@Post
	@Publico
	public void login(String login, String senha) {
		validator.onErrorUsePageOf(HomeController.class).index();
		Cliente cliente = clienteDao.getClienteFromLogin(login, senha);
		if(cliente  == null){
			validator.add(new SimpleMessage("mensagem", "Login inválido!"));
			result.forwardTo(HomeController.class).index();
			return;
		}
		clienteInfo.login(cliente);
		result.forwardTo(TicketController.class).ticketsAbertosCliente();
	}

	@Consumes({"application/xml", "application/json"})
	@Post("/cliente/cadastrar")
	@Publico
	public void cadastrarCliente(Cliente cliente, String tokenInformado) {
		if (token.equals(tokenInformado)) {
			clienteDao.cadastrar(cliente);
			result.use(Results.status()).ok();
		} else{
			result.use(Results.status()).forbidden("Token informado incorretamente");
		}
		validator.onErrorSendBadRequest();
	}

	public void listarXml() {
		result.use(Results.xml()).from(clienteDao.listarTodos()).serialize();
	}
}
