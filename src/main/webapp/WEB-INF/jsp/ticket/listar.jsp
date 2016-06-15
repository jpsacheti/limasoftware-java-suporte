<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="time" uri="http://sargue.net/jsptags/time"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Suporte Online</title>
<%@include file="/header.jsp" %>
<script>
	$("#ticket .remover").click(function(event){
		if(confirm("Tem certeza que deseja excluir?")){
			var tag = $(this).closest(".ticket");
			$.ajax({
				url: $(this).attr("href"),
				type: 'DELETE'
			}).done(function(data, jqXHR, textStatus)){
				tag.fadeOut();
			}).fail(function(jqXHR, textStatus, err){
				alert('Falha ao excluir! '+err);
			});
		}
		event.preventDefault();
	})
</script>
</head>
<body>
	<div class="container center-block">
		<h3>Tickets - ${clienteInfo.cliente.fantasia}</h3>
		<c:choose>
			<c:when test="${empty ticketList }">
				<h4>Não foram encontrados tickets...</h4>
			</c:when>
			<c:otherwise>
				<table class="table table-hover" id="tickets">
					<tr>
						<th>Código</th>
						<th>Data</th>
						<th>Operador</th>
						<th>Finalizado</th>
						<th>Visualizar</th>
						<th>Excluir</th>
						<th>Editar</th>
					</tr>
					<c:forEach items="${ticketList}" var="ticket">
						<tr class="ticket">
							<td>${ticket.codigo}</td>
							<td><time:format value="${ticket.criacao}"
									pattern="dd/MM/yyyy" /></td>
							<td>${empty ticket.operador ? 'Não designado' : ticket.operador.nome }</td>
							<td>${ticket.finalizado ? 'Sim' : 'Não'}</td>
							<td><a href="${linkTo[TicketController].visualizar(ticket)}"
								class="label"><span class="glyphicon glyphicon-search"></span></a>
							</td>
							<td><a class="remove" href="${linkTo[TicketController].remover(ticket.codigo)}"></a></td>
						</tr>
					</c:forEach>
				</table>

			</c:otherwise>
		</c:choose>
		
	</div>
</body>
</html>