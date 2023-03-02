<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<jsp:include page="head.jsp"></jsp:include>

  <body>
  <!-- Pre-loader start -->
  <jsp:include page="theme-loader.jsp"></jsp:include>
  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
          
          <jsp:include page="navbarfile.jsp"></jsp:include>

          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
              
                  <jsp:include page="navbarmymenu.jsp"></jsp:include>
                  
                  <div class="pcoded-content">
                      <!-- Page-header start -->
                      <jsp:include page="page-header.jsp"></jsp:include>
                      <!-- Page-header end -->
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                            <!-- task, page, download counter  start -->
                                            <div class="row">
                                            	<div class="col-sm-12">
                                                <!-- Basic Form Inputs card start -->
                                                	<div class="card">
                                                    	<div class="card-block">
                                                        	<h4 class="sub-title">Cadastro</h4>
			                                            	<form action="<%= request.getContextPath() %>/ServletUsuarioController" method="post" id="formUser">
					                                            
					                                            <input type="hidden" name="acao" id="acao" value="0">
					                                            
					                                            <div class="form-group row">
                                                                	<div class="col-sm-6">
                                                                		<label class="col-form-label">ID</label>
                                                                    	<input type="text" name="id" id="id" class="form-control" readonly="readonly" value="${ login.id }">
                                                                    </div>
                                                                    <div class="col-sm-6">
                                                                    	<label class="col-form-label">Usuario</label>
                                                                        <input type="text" name="username" id="username" class="form-control" placeholder="Usuario" required="required" value="${login.username}">
                                                                    </div>
                                                                    <div class="col-sm-6">
                                                                		<label class="col-form-label">Nome</label>
                                                                    	<input type="text" name="nome" id="nome" class="form-control" placeholder="Nome"  required="required" required="required" value="${ login.nome }">
                                                                    </div>
                                                                    <div class="col-sm-6">
                                                                    	<label class="col-form-label">E-mail</label>
                                                                        <input type="email" name="email" id="email" class="form-control" placeholder="E-mail" required="required" value="${ login.email }">
                                                                    </div>
                                                                    <div class="col-sm-6">
                                                                		<label class="col-form-label">Senha</label>
                                                                    	<input type="password" name="password" id="password" class="form-control" placeholder="Senha"  required="required" value="${ login.password }">
                                                                    </div>
                                                                    <div class="col-sm-6">
                                                                    	<label class="col-form-label">Telefone</label>
                                                                        <input type="text" name="telefone" id="telefone" class="form-control" placeholder="Telefone" value="${ login.telefone }">
                                                                    </div>
                                                                </div>
                                                                <input type="button" class="btn btn-primary waves-effect waves-light" value="Limpar campos" onclick="limparForm();">
				                                            	<button class="btn btn-success waves-effect waves-light">Salvar</button>
													            <button type="button" class="btn btn-danger waves-effect waves-light" onclick="criarDelete()">Excluir</button>
				                                            </form>
                                            			</div>
                                            		</div>
                                            	</div>
                                            <!--  project and team member end -->
                                        </div>
                                    <span>${msg}</span>
                                    </div>
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

<jsp:include page="jsfile.jsp"></jsp:include>

<script type="text/javascript">

function limparForm() {

	var elements = document.getElementById("formUser").elements;
	for (i = 0; i < 6; i++) {
		elements[i].value = "";
	}
}

function criarDelete() {
	alert("oi")
	document.getElementById("formUser").method = 'get';
	document.getElementById("acao").value = "deletar";
	document.getElementById("formUser").submit();
}

</script>

</body>

</html>
