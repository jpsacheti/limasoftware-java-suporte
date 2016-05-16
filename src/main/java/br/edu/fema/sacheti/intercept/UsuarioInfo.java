package br.edu.fema.sacheti.intercept;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.edu.fema.sacheti.model.Usuario;

@SessionScoped
@Named
public class UsuarioInfo implements Serializable{
	
	private static final long serialVersionUID = 6078821766905465970L;
	private Usuario usuario;
	
	public void login(Usuario usuario){
		this.usuario = usuario;
	}
	
	public void logout(){
		this.usuario = null;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
}
