package br.edu.fema.sacheti.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

import br.edu.fema.sacheti.jpa.LocalDatePersistenceConverter;

@SuppressWarnings("serial")
@Entity
public class Ticket implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	
	@ManyToOne(targetEntity=Cliente.class, optional=false)
	private Cliente cliente;
	@ManyToOne(targetEntity=Operador.class)
	private Operador operador;
	
	@OneToMany(targetEntity=Interacao.class, orphanRemoval=true, fetch=FetchType.EAGER)
	private List<Interacao> interacoes = new ArrayList<>();
	@Convert(converter=LocalDatePersistenceConverter.class)
	private LocalDate criacao;
	private boolean finalizado;
	@NotEmpty
	private String descricao;
	@Enumerated(EnumType.ORDINAL)
	private Prioridade prioridade;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Operador getOperador() {
		return operador;
	}
	public void setOperador(Operador operador) {
		this.operador = operador;
	}
	public List<Interacao> getInteracoes() {
		return interacoes;
	}
	public void setInteracoes(List<Interacao> interacoes) {
		this.interacoes = interacoes;
	}
	public LocalDate getCriacao() {
		return criacao;
	}
	public void setCriacao(LocalDate criacao) {
		this.criacao = criacao;
	}
	public boolean isFinalizado() {
		return finalizado;
	}
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
