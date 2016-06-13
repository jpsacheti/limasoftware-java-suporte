<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/header.jsp"%>
<title>Suporte Online</title>
</head>
<body>
	<div class="container">
		<form action="${linkTo[ClienteController].login}"
			class="col-md-offset-4 col-md-4" method="post">
			<h3 class="center-block" align="center">Login de usuário</h3>
			<div class="form-group">
				<input type="text" class="form-control" id="login" name="login"
					placeholder="Login" />
			</div>
			<div class="form-grou">
				<input type="password" class="form-control" id="senha" name="senha"
					placeholder="Senha" />
			</div>

			<button class="btn btn-primary btn-large center-block"
				style="margin-top: 20px" type="submit" id="submit">
				<i class="fa fa-sign-in" aria-hidden="true"></i> Login
			</button>
		</form>
	</div>
</body>
</html>