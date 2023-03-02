<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error</title>
<style type="text/css">

	body {
	  font-family: Arial, sans-serif;
	  background-color: #f5f5f5;
	  display: flex;
	  align-items: center;
	  justify-content: center;
	  height: 100vh;
	}
	
	.container {
	  max-width: 800px;
	  padding: 50px;
	  background-color: #fff;
	  border: 1px solid #ccc;
	  border-radius: 5px;
	  box-shadow: 0 2px 5px rgba(0,0,0,0.1);
	}
	
	h1 {
	  font-size: 36px;
	  margin: 0 0 20px 0;
	}
	
	p {
	  font-size: 24px;
	  margin: 0;
	  color: #888;
	}

</style>
</head>
<body>
	<div class="container">
      <h1>Erro Genérico</h1>
      <p>Ocorreu um erro. Por favor, tente novamente mais tarde.</p>
      <p><% out.print(request.getAttribute("msg")); %></p>
    </div>
</body>
</html>