package br.edu.fema.sacheti.controllers;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.edu.fema.sacheti.dao.OperadorDao;
import br.edu.fema.sacheti.intercept.OperadorInfo;

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
		result.redirectTo(TicketController.class).ticketsAbertos();
	}

}
