<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<title>Insert title here</title>

<style>
		body {
			margin: 0;
			padding: 0;
			background: url("https://img.freepik.com/free-photo/abstract-grunge-decorative-relief-navy-blue-stucco-wall-texture-wide-angle-rough-colored-background_1258-28311.jpg?w=1380&t=st=1677544623~exp=1677545223~hmac=3b6c18771ecc297e930f051a67931d5742f014a338d3033a2bf8b33ebbd29dc9") no-repeat center center fixed;
			background-size: cover;
			font-family: sans-serif;
		}
		
		.container {
			width: 400px;
			margin: 0 auto;
			margin-top: 80px;
			background-color: #fff;
			padding: 30px;
			border-radius: 8px;
			box-shadow: 0 0 20px rgba(0,0,0,0.3);
			display: flex;
			flex-direction: column;
			align-items: center;
		}
		
		.container h2 {
			font-size: 2rem;
			font-weight: bold;
			margin-bottom: 30px;
			text-align: center;
			color: #343a40;
			text-transform: uppercase;
			letter-spacing: 1px;
		}
		
		input[type="text"], input[type="password"] {
			width: 100%;
			padding: 12px 20px;
			margin: 10px 0;
			display: inline-block;
			border: none;
			border-bottom: 2px solid #eee;
			background-color: #f8f9fa;
			font-size: 1rem;
			color: #495057;
			transition: border-bottom-color 0.2s ease-in-out;
		}
		
		input[type="text"]:focus, input[type="password"]:focus {
			border-bottom-color: #007bff;
			outline: none;
		}
		
		.button {
			background-color: #007bff;
			color: #fff;
			padding: 12px 20px;
			margin-top: 30px;
			border: none;
			border-radius: 4px;
			cursor: pointer;
			width: 100%;
			font-size: 1.1rem;
			font-weight: bold;
			text-transform: uppercase;
			letter-spacing: 1px;
			transition: background-color 0.2s ease-in-out;
		}
		
		.button:hover {
			background-color: #0069d9;
		}
		
		.container p {
			font-size: 0.9rem;
			text-align: center;
			margin-top: 20px;
			color: #495057;
		}
		
		.container a {
			color: #007bff;
			text-decoration: none;
			transition: color 0.2s ease-in-out;
		}
		
		.container a:hover {
			color: #0069d9;
		}
		
		.msg {
			color: red;
			top:50px;
		}
		
	</style>
</head>
<body>
	<div class="container">
		<h2>Login</h2>
		<form action="<%= request.getContextPath() %>/ServletLogin" method="post">
			<label for="username">Nome de Usuário:</label>
			<input type="text" id="username" name="username" required="required">
			
			<label for="password">Senha:</label>
			<input type="password" id="password" name="password" required="required">
			
			<input class="button" type="submit" value="Acessar">
		</form>
		<p>Não tem uma conta? <a href="#">Cadastre-se</a></p>
		<h5 class="msg">${msg}</h5>
	</div>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>