<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Suporte Online</title>
<%@ include file="/header.jsp"%>
</head>
<body>
	<div class="container">
		<h3 class="text-center">Incluir Interação - Ticket
			${ticket.codigo}</h3>
		<form method="post"
			action="${linkTo[TicketController].incluirInteracao(ticket, interacao)}">
			<div class="form-group">
				<label for="">Descrição</label> <input type="text" class=""
					name="interacao.descricao" />
			</div>
			<div class="pull-right">
				<button type="submit" class="btn btn-success"
					style="margin-right: 5px;">
					<span class="glyphicon glyphicon-ok"></span> Gravar
				</button>
				<a href="${linkTo[TicketController].ticketsAbertosCliente}"
					class="btn"><span class="glyphicon glyphicon-chevron-left"></span> Voltar</a>
			</div>
		</form>
	</div>
</body>
</html>