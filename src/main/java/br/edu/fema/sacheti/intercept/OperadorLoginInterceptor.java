package br.edu.fema.sacheti.intercept;

import javax.inject.Inject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.caelum.vraptor.view.Results;
import br.edu.fema.sacheti.dao.UsuarioDao;
import br.edu.fema.sacheti.model.Usuario;

@Intercepts
public class OperadorLoginInterceptor {
	private final OperadorInfo operadorInfo;
	private final UsuarioDao usuarioDao;
	private final Result result;
	
	@Inject
	public OperadorLoginInterceptor(OperadorInfo operadorInfo, UsuarioDao usuarioDao, Result result) {
		this.operadorInfo = operadorInfo;
		this.usuarioDao = usuarioDao;
		this.result = result;
	}
	
	/**	
	 * @deprecated CDI eyes only
	 */
	public OperadorLoginInterceptor(){
		this(null, null, null);
	}
	
	@Accepts
	public boolean accepts(ControllerMethod method){
		return method.containsAnnotation(Admin.class) && !method.containsAnnotation(Publico.class);
	}
	
	
	@AroundCall
	public void intercept(SimpleInterceptorStack sis) {
		Usuario logado = operadorInfo.getUsuario();
		try{
			usuarioDao.refresh(logado);
		} catch(Exception e){
			System.err.println("Erro ao validar operador autenticado: "+e.getMessage());
		}
		if(logado == null){
			result.use(Results.status()).forbidden("Acesso restrito a usuários logados");
			return;
		}
		sis.next();
	}
	
}
