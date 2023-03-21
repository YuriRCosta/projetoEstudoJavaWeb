<%@page import="model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-br">

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
			                                            	<form action="<%= request.getContextPath() %>/ServletUsuarioController" enctype="multipart/form-data" method="post" id="formUser">
					                                            
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
                                                                		<label class="col-form-label">Data de Nascimento</label>
                                                                    	<input type="text" name="dataNascimento" id="dataNascimento" class="form-control" placeholder="00/00/0000"  required="required" value="${ login.dataNascimento }">
                                                                    </div>
                                                                    <div class="col-sm-6">
                                                                		<label class="col-form-label">Renda Mensal</label>
                                                                    	<input type="text" name="rendaMensal" id="rendaMensal" class="form-control" placeholder="R$"  required="required" value="${ login.rendaMensal }">
                                                                    </div>
                                                                    <div class="col-sm-6">
                                                                		<label class="col-form-label">CEP</label>
                                                                    	<input type="text" onblur="pesquisaCep()" name="cep" id="cep" class="form-control" placeholder="CEP"  required="required" value="${ login.cep }">
                                                                    </div>
                                                                    <div class="col-sm-6">
                                                                		<label class="col-form-label">Logradouro</label>
                                                                    	<input type="text" name="logradouro" id="logradouro" class="form-control" placeholder="Rua"  required="required" value="${ login.logradouro }">
                                                                    </div>
                                                                    <div class="col-sm-6">
                                                                		<label class="col-form-label">Bairro</label>
                                                                    	<input type="text" name="bairro" id="bairro" class="form-control" placeholder="Bairro"  required="required" value="${ login.bairro }">
                                                                    </div>
                                                                    <div class="col-sm-6">
                                                                		<label class="col-form-label">Localidade</label>
                                                                    	<input type="text" name="localidade" id="localidade" class="form-control" placeholder="Cidade"  required="required" value="${ login.localidade }">
                                                                    </div>
                                                                    <div class="col-sm-6">
                                                                		<label class="col-form-label">UF</label>
                                                                    	<input type="text" name="uf" id="uf" class="form-control" placeholder="Estado"  required="required" value="${ login.uf }">
                                                                    </div>
                                                                    <div class="col-sm-6">
                                                                		<label class="col-form-label">Numero</label>
                                                                    	<input type="text" name="numero" id="numero" class="form-control" placeholder="Numero"  required="required" value="${ login.numero }">
                                                                    </div>
                                                                    <div class="col-sm-6">
                                                                    	<label class="col-form-label">Telefone</label>
                                                                        <input type="text" name="telefone" id="telefone" required="required" class="form-control" placeholder="Telefone" value="${ login.telefone }">
                                                                    </div>
                                                                    <div class="col-sm-3">
                                                                    	<label class="col-form-label">Perfil</label>
	                                                                    <select class="form-control" aria-label="Default select example" name="perfil">
																			<option selected disabled="disabled">Selecione o perfil</option>
																			
																			<option value="ADMIN" <% 
																			
																			ModelLogin login = (ModelLogin) request.getAttribute("login");
																			
																			if(login != null && login.getPerfil().equals("ADMIN")) {
																				
																				out.print(" ");
																				out.print("selected=\"selected\"");
																				out.print(" ");
																				
																			} %>>Admin</option>
																			
																			<option value="SECRETARIA" <% 
																			
																			
																				if(login != null && login.getPerfil().equals("SECRETARIA")) {
																				
																					out.print(" ");
																					out.print("selected=\"selected\"");
																					out.print(" ");
																					
																			} %>>Secretaria</option>
																			
																			<option value="AUXILIAR"<% 
																			
																			
																				if(login != null && login.getPerfil().equals("AUXILIAR")) {
																				
																					out.print(" ");
																					out.print("selected=\"selected\"");
																					out.print(" ");
																					
																			} %>>Auxiliar</option>
																			
																		</select>
																	</div>
																	<div class="col-sm-3">
																	  <label for="formFile" class="form-label" style="margin-top: 8px;">Adicione sua foto de perfil</label>
																	  <input class="form-control" id="fileFoto" name="fileFoto" accept="image/*" type="file" id="formFile">
																	</div>
																<div class="col-sm-3">
																  <input type="radio" name="sexo" value="MASCULINO" style="margin-left: 10%; margin-top: 10%;"<% 
																			
																			
																				if(login != null && login.getSexo().equals("MASCULINO")) {
																				
																					out.print(" ");
																					out.print("checked=\"checked\"");
																					out.print(" ");
																					
																			} %>checked>
																  <label>Masculino</label>
																  <input type="radio" name="sexo" value="FEMININO" style="margin-left: 10%; margin-top: 10%;"<% 
																			
																			
																				if(login != null && login.getSexo().equals("FEMININO")) {
																				
																					out.print(" ");
																					out.print("checked=\"checked\"");
																					out.print(" ");
																					
																			} %>>
																  <label>Feminino</label>
																</div>
																
															</div>
                                                                <input type="button" class="btn btn-primary waves-effect waves-light" value="Limpar campos" onclick="limparForm();">
				                                            	<button class="btn btn-success waves-effect waves-light">Salvar</button>
													            <button type="button" class="btn btn-danger waves-effect waves-light" onclick="criarDeleteAjax()">Excluir</button>
				                                            	<button type="button" class="btn btn-info" data-toggle="modal" data-target="#exampleModal">Pesquisar</button>
				                                            	
				                                            	<c:if test="${ login.id > 0 }">
				                                            		<a href="<%= request.getContextPath() %>/ServletTelefone?iduser=${ login.id }" class="btn btn-success waves-effect waves-light">Telefone</a>
				                                           	 	</c:if>
				                                            </form>
                                            			</div>
                                            		</div>
                                            	</div>
                                            <!--  project and team member end -->
                                        </div>
                                    <span id="msg">${ msg }</span>

										<div style="height: 300px; overflow: scroll; overflow-x: hidden;">
											<table class="table" id="tabelaResultadosView">
												<thead>
													<tr>
														<th style="text-align: center;" scope="col">ID</th>
														<th style="text-align: center;" scope="col">Nome</th>
														<th style="text-align: center;" scope="col">Ver</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${ logins }" var="ml">
														<tr>
														<td style="text-align:center;"><c:out value="${ ml.id }"></c:out></td>
														<td style="text-align:center;"><c:out value="${ ml.nome }"></c:out></td>
														<td style="text-align:center;"><a class="btn btn-info" href="<%=request.getContextPath() %>/ServletUsuarioController?acao=buscarEditar&id=${ml.id }">Detalhes</a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<nav aria-label="Page navigation example">
										  <ul class="pagination" style="justify-content:center;">
										  	<%
										  	
										  		int totalPagina = (int) request.getAttribute("totalPagina");
										  	
										  		for(int p=0; p < totalPagina; p++) {
										  			String url = request.getContextPath() + "/ServletUsuarioController?acao=paginar&pagina=" + (p*5);
										  			out.print("<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"\">"+(p + 1	)+"</a></li>");
										  		}
										  	
										  	%>
										  </ul>
										</nav>
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

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Nome</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="input-group mb-3">
  			<input type="text" class="form-control" placeholder="Nome" id="nomeBusca" aria-label="Recipient's username" aria-describedby="basic-addon2">
  			<div class="input-group-append">
    			<button class="btn btn-outline-info" type="button" onclick="buscarUsuario()">Pesquisar</button>
  			</div>
		</div>
      </div>
      <div style="height:300px; overflow: scroll; overflow-x: hidden;">
	      <table class="table" id="tabelaResultados">
			  <thead>
			    <tr>
			      <th style="text-align:center;" scope="col">ID</th>
			      <th style="text-align:center;" scope="col">Nome</th>
			      <th style="text-align:center;" scope="col">Ver</th>
			    </tr>
			  </thead>
			  <tbody>
			    
			  </tbody>
			</table>
		</div>
      <div class="modal-footer" style="justify-content:space-between;">
  		<span id="totalResultados"></span>  
  		<nav aria-label="Page navigation example">
		  <ul class="pagination" id="paginacaoAjax" style="justify-content:center;">
				
		  </ul>
		</nav>  
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>	
</div>

<script type="text/javascript">

$( function() {
	  
	  $("#dataNascimento").datepicker({
		    dateFormat: 'dd/mm/yy',
		    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
		    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
		    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
		    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
		    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
		    nextText: 'Próximo',
		    prevText: 'Anterior'
		});
} );

function pesquisaCep() {
	var cep = $("#cep").val();
	
	$.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {
		
		if (!("erro" in dados)) {
            $("#cep").val(dados.cep);
            $("#logradouro").val(dados.logradouro);
            $("#bairro").val(dados.bairro);
            $("#localidade").val(dados.localidade);
            $("#uf").val(dados.uf);
        } else {
            limpa_formulário_cep();
            alert("CEP não encontrado.");
        }
		
	});
}

$("#rendaMensal").maskMoney({showSymbol:true, symbol:"R$ ", decimal:",", thousands:"."});

const formatter = new Intl.NumberFormat('pt-BR', {
	currency : 'BRL',
	minimumFractionDigits : 2
});

$("#rendaMensal").val(formatter.format($("#rendaMensal").val()));

$("#rendaMensal").focus();

var dataNascimento = $("#dataNascimento").val();

if(dataNascimento != null && dataNascimento != '') {
	
	var dateFormat = new Date(dataNascimento);

	$("#dataNascimento").val(dateFormat.toLocaleDateString('pt-BR', {timeZone: 'UTC'}));
	
}

$("#username").focus();

$("#numero").keypress(function (event) {
	return /\d/.test(String.fromCharCode(event.keyCode));
});

$("#cep").keypress(function (event) {
	return /\d/.test(String.fromCharCode(event.keyCode));
});

function detalhesEditar(id) {
	
	var urlAction = document.getElementById("formUser").action;
	
	window.location.href = urlAction + "?acao=buscarEditar&id="+ id;
	
}

function limparForm() {

	var elements = document.getElementById("formUser").elements;
	for (i = 0; i <= 6; i++) {
		elements[i].value = "";
	}
}

function criarDeleteAjax () {
	
	if(confirm("Deseja realemente excluir os dados?")) {
		
		var urlAction = document.getElementById("formUser").action;
		var idUser = document.getElementById("id").value;
		
		$.ajax({
			
			method: "get",
			url : urlAction,
			data : "id=" + idUser + "&acao=deletarajax",
			success: function(response) {
				limparForm();
				document.getElementById('msg').textContent = response;
			}
			
		}).fail(function(xhr, status, errorThrown){
			alert("Erro ao deletar usuario por ID: " + xhr.responseText);
		});
		
	}
	
}

function criarDelete() {
	
	if(confirm("Deseja realemente excluir os dados?")) {
		
		document.getElementById("formUser").method = 'get';
		document.getElementById("acao").value = "deletar";
		document.getElementById("formUser").submit();
		
	}
	
}

function buscaUserPageAjax(url){
	var nomeBusca = document.getElementById("nomeBusca").value;
	var urlAction = document.getElementById("formUser").action;

	$.ajax({
		
		method: "get",
		url : urlAction,
		data: url,
		success: function(response, textStatus, xhr) {
			var json = JSON.parse(response);
			
			$('#tabelaResultados > tbody > tr').remove();
			$('#paginacaoAjax > li').remove();

			for(var p = 0; p < json.length; p++) {
				$('#tabelaResultados > tbody').append('<tr> <td style="text-align:center;vertical-align: middle;">'+json[p].id+'</td> <td style="text-align:center;vertical-align: middle;">'+json[p].nome+'</td> <td style="text-align:center;"><button onclick="detalhesEditar('+json[p].id+')" type="button" class="btn btn-info">Detalhes</button></td> </tr>');

			}
			
			document.getElementById("totalResultados").textContent = "Resultados: " + json.length;
			
			var totalPagina = xhr.getResponseHeader("totalPagina");
	
			for(var p = 0; p < totalPagina; p++) {

				var url = 'nomeBusca=' + nomeBusca + '&acao=buscarUserAjaxPage&pagina=' + (p * 5);
				$("#paginacaoAjax").append('<li class="page-item"><a href="#" class="page-link" onclick="buscaUserPageAjax(\''+url+'\')">'+ (p + 1)+'</a></li>');
				
			}

		}
		
	}).fail(function(xhr, status, errorThrown){
		alert("Erro ao buscar usuario por nome: " + xhr.responseText);
	});
	
}

function buscarUsuario() {
	
	var nomeBusca = document.getElementById("nomeBusca").value;

	if(nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != '') {
		var urlAction = document.getElementById("formUser").action;
		
		$.ajax({
			
			method: "get",
			url : urlAction,
			data : "nomeBusca=" + nomeBusca + "&acao=buscarUserAjax",
			success: function(response, textStatus, xhr) {
				var json = JSON.parse(response);
				
				
				$('#tabelaResultados > tbody > tr').remove();
				$("#paginacaoAjax > li").remove();
				
				for(var p = 0; p < json.length; p++) {
					$('#tabelaResultados > tbody').append('<tr> <td style="text-align:center;vertical-align: middle;">'+json[p].id+'</td> <td style="text-align:center;vertical-align: middle;">'+json[p].nome+'</td> <td style="text-align:center;"><button onclick="detalhesEditar('+json[p].id+')" type="button" class="btn btn-info">Detalhes</button></td> </tr>');

				}
				
				document.getElementById("totalResultados").textContent = "Resultados: " + json.length;
				
				var totalPagina = xhr.getResponseHeader("totalPagina");
				
				for(var p = 0; p < totalPagina; p++) {
					var url = 'nomeBusca=' + nomeBusca + '&acao=buscarUserAjaxPage&pagina=' + (p * 5);
					$("#paginacaoAjax").append('<li class="page-item"><a href="#" class="page-link" onclick="buscaUserPageAjax(\''+url+'\')">'+ (p + 1)+'</a></li>');
					
				}
			}
			
		}).fail(function(xhr, status, errorThrown){
			alert("Erro ao buscar usuario por nome: " + xhr.responseText);
		});
		
	}

}

</script>

</body>

</html>
