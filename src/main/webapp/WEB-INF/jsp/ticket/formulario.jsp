<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Suporte Online</title>
<%@include file="/header.jsp"%>
</head>
<body>
	<div class="container">
		<div class="center-block">
			<h3>Cadastro de Ticket</h3>
			<form method="post" action="${linkTo[TicketController].gravar}">
				<div class="form-group">
					<textarea rows="30" cols="30" class="form-control"
						placeholder="Descricao" name="ticket.descricao"></textarea>
				</div>
				<button class="btn btn-success" type="submit">
					<i class="fa fa-floppy-o" aria-hidden="true"></i> Salvar
				</button>
			</form>
		</div>
	</div>
</body>
</html>