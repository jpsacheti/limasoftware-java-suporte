package br.edu.fema.sacheti.intercept;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.edu.fema.sacheti.model.Operador;
import br.edu.fema.sacheti.model.Usuario;

@SessionScoped
@Named
public class OperadorInfo implements Serializable {
	
	private static final long serialVersionUID = 8617416976095097667L;
	private Operador operador;
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public Operador getOperador(){
		return operador;
	}
	
	public void logout(){
		this.usuario = null;
	}
	
	public void login(Operador operador){
		this.operador = operador;
		this.usuario = operador.getUsuario();
	}

}
