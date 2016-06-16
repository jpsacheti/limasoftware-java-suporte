<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="time" uri="http://sargue.net/jsptags/time"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Suporte Online</title>
<%@include file="/header.jsp"%>
</head>
<body>
	<div class="container">
		<c:if test="${not empty mensagem }">
			<p class="text-center">${mensagem}</p>
		</c:if>
		<div class="panel panel-default">
			<div class="panel-title">Ticket</div>
			<div class="panel-body">
				<ul class="list-unstyled">
					<li><b>Data de Criação: </b>
						<p>
							<time:format value="${ticket.criacao}" pattern="dd/MM/yyyy" />
						</p></li>
					<li><b>Operador: </b> ${empty ticket.operador ? 'Não designado' : ticket.operador.nome }</li>
					<li><b>Finalizado?</b> ${ticket.finalizado? 'Sim' : 'Não' }</li>
					<li><b>Descrição</b></li>
					<li>${ticket.descricao}</li>
				</ul>
				<c:if test="${not empty ticket.interacoes}">
					<c:forEach items="${ticket.interacoes}" var="interacao">
						<hr>
						<ul>
							<li><b>Usuario: </b>${interacao.usuario.nome}</li>
							<li><b>Data: </b> <time:format
									value="${interacao.dataInteracao}" pattern="dd/MM/yyyy" /></li>
							<li><b>Descrição: </b> ${interacao.descricao}</li>
						</ul>
					</c:forEach>
				</c:if>
			</div>
			<div class="panel-footer">
				<a class="btn btn-primary" style="margin-right: 5px;"
					href="${linkTo[TicketController].ticketsAbertosCliente}"> <span
					class="glyphicon glyphicon-chevron-left"></span> Voltar
				</a>
				<c:if test="${not ticket.finalizado }">
					<a href="${linkTo[TicketController].finalizar(ticket)}"
						class="btn btn-success"><span class="glyphicon glyphicon-ok"></span>
						Finalizar</a>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>