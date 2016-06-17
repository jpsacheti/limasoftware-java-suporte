<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="time" uri="http://sargue.net/jsptags/time"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Suporte Online</title>
<%@include file="/header.jsp"%>
</head>
<body>
	<div class="container center-block">
		<h3>Tickets - ${clienteInfo.cliente.fantasia}</h3>
		<c:choose>
			<c:when test="${empty ticketList }">
				<h4>Não foram encontrados tickets...</h4>
			</c:when>
			<c:otherwise>
				<c:if test="${not empty mensagem}">
					<div class="alert alert-success alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<strong>${mensagem}</strong>
					</div>
				</c:if>
				<table class="table table-hover" id="tickets">
					<tr>
						<th>Código</th>
						<th>Data</th>
						<th>Operador</th>
						<th>Finalizado</th>
						<th>Visualizar</th>
						<th>Excluir</th>
						<th>Finalizer</th>
					</tr>
					<c:forEach items="${ticketList}" var="ticket">
						<tr class="ticket">
							<td>${ticket.codigo}</td>
							<td><time:format value="${ticket.criacao}"
									pattern="dd/MM/yyyy" /></td>
							<td>${empty ticket.operador ? 'Não designado' : ticket.operador.nome }</td>
							<td>${ticket.finalizado ? 'Sim' : 'Não'}</td>
							<td><a
								href="${linkTo[TicketController].visualizar(ticket.codigo)}"
								class="label"><span class="glyphicon glyphicon-search"
									style="color: black;"></span></a></td>
							<td><a class="remove"
								href="${linkTo[TicketController].remover(ticket.codigo)}"><span
									class="glyphicon glyphicon-trash" style="color: black;"></span></a></td>
							<td><a href="${linkTo[TicketController].finalizar(ticket.codigo)}"><span
									class="glyphicon glyphicon-ok"></span></a>
							</td>
						</tr>
					</c:forEach>
				</table>

			</c:otherwise>
		</c:choose>
		<div class="center-block">
			<a href="${linkTo[TicketController].cadastrar}"
				class="btn btn-success"><span class="glyphicon glyphicon-plus"></span>
				Adicionar</a>
		</div>
	</div>
</body>
</html>