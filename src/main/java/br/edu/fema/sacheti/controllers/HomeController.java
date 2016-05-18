package br.edu.fema.sacheti.controllers;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.edu.fema.sacheti.dao.UsuarioDao;
import br.edu.fema.sacheti.intercept.Publico;
import br.edu.fema.sacheti.model.Usuario;

@Controller
public class HomeController {

	@Inject
	private Result result;
	
	@Inject
	private UsuarioDao dao;

	@Publico
	@Path("/")
	public void index() {
		Usuario usr = new Usuario();
		usr.setAtivo(true);
		usr.setLogin("teste");
		usr.setNome("Joao");
		usr.setSenha("teste2");
		dao.cadastrar(usr);
		System.err.println("Funcionou?");
		result.include("msg", "Message from your controller");
	}
}