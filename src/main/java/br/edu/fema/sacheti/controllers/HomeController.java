package br.edu.fema.sacheti.controllers;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.edu.fema.sacheti.intercept.Publico;

@Controller
public class HomeController {

	@Inject
	private Result result;
	
	@Publico
	@Path("/")
	public void index() {
		result.include("teste","Teste");
	}
}