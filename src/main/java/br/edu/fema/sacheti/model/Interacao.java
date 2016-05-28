package br.edu.fema.sacheti.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.edu.fema.sacheti.jpa.LocalDatePersistenceConverter;

@SuppressWarnings("serial")
@Entity
public class Interacao implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	@ManyToOne(targetEntity=Ticket.class)
	private Ticket ticket;
	private String descricao;
	@Convert(converter=LocalDatePersistenceConverter.class)
	private LocalDate dataInteracao;
	private Usuario usuario;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDate getData() {
		return dataInteracao;
	}
	public void setData(LocalDate data) {
		this.dataInteracao = data;
	}
	public LocalDate getDataInteracao() {
		return dataInteracao;
	}
	public void setDataInteracao(LocalDate dataInteracao) {
		this.dataInteracao = dataInteracao;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
