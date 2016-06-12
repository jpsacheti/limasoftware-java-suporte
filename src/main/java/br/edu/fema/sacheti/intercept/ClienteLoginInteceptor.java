package br.edu.fema.sacheti.intercept;

import javax.inject.Inject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.caelum.vraptor.view.Results;
import br.edu.fema.sacheti.dao.ClienteDao;
import br.edu.fema.sacheti.model.Usuario;

@Intercepts
public class ClienteLoginInteceptor {
	
	private final ClienteInfo clienteInfo;
	private final Result result;
	private final ClienteDao dao;
	
	
	@Inject
	public ClienteLoginInteceptor(ClienteInfo info, Result result, ClienteDao dao) {
		this.clienteInfo = info;
		this.result = result;
		this.dao = dao;
	}
	
	
	/**
	 * @deprecated CDI eyes only
	 */
	public ClienteLoginInteceptor() {
		this(null, null, null);
	}
	
	@Accepts
	public boolean accepts(ControllerMethod method){
		return !method.containsAnnotation(Publico.class) && !method.containsAnnotation(Admin.class);
	}
	
	@AroundCall
	public void intercept(SimpleInterceptorStack sis) {
		Usuario logado = clienteInfo.getUsuario();
		try{
			dao.refresh(clienteInfo.getCliente());
		} catch(Exception e){
			System.err.println("Erro ao validar cliente autenticado: ");
			e.printStackTrace();
		}
		if(logado == null){
			result.use(Results.status()).forbidden("Acesso restrito a usuários logados");
			return;
		}
		sis.next();
	}

}
