package br.edu.fema.sacheti.intercept;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.edu.fema.sacheti.model.Cliente;
import br.edu.fema.sacheti.model.Usuario;

@SessionScoped
@Named
public class ClienteInfo implements Serializable{
	
	private static final long serialVersionUID = 6078821766905465970L;
	private Cliente cliente;
	private Usuario usuario;
	
	public void login(Cliente cliente){
		this.cliente = cliente;
		this.usuario = cliente.getUsuario();
	}
	
	public void logout(){
		this.usuario = null;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public Cliente getCliente(){
		return cliente;
	}
}
