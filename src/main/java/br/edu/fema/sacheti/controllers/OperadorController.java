package br.edu.fema.sacheti.controllers;

import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.edu.fema.sacheti.dao.OperadorDao;
import br.edu.fema.sacheti.intercept.OperadorInfo;
import br.edu.fema.sacheti.intercept.Publico;
import br.edu.fema.sacheti.model.Operador;

@Controller
public class OperadorController {
	
	private final OperadorDao operadorDao;
	private final OperadorInfo operadorInfo;
	private final Validator validator;
	private final Result result;
	 
	/**
	 * @Deprecated CDI eyes only 
	 */
	public OperadorController() {
		this(null, null, null, null);
	}
	
	@Inject
	public OperadorController(OperadorDao operadorDao, OperadorInfo operadorInfo,
			Validator validator, Result result) {
		this.operadorDao = operadorDao;
		this.operadorInfo = operadorInfo;
		this.validator = validator;
		this.result = result;
	}
	
	@Post
	public void login(String login, String senha){
		validator.onErrorForwardTo(HomeController.class).index();
		operadorInfo.login(operadorDao.getOperadorFromLogin(login, senha));
		result.redirectTo(TicketController.class).ticketsResolver();
	}
	
	@Consumes("application/xml")
	@Post
	@Publico
	public void cadastrar(Operador operador){
		operadorDao.cadastrar(operador);
		result.use(Results.status()).ok();
		validator.onErrorSendBadRequest();
	}
	
	public void listarXml(){
		result.use(Results.xml()).from(operadorDao.listarTodos()).serialize();
	}
	

}
