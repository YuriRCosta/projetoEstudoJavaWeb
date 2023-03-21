<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
                                        <div class="row">
                                           	<div class="col-sm-12">
                                            <!-- Basic Form Inputs card start -->
                                               	<div class="card">
                                                   	<div class="card-block">
                                                		<h4 class="sub-title">Cadastro Telefone</h4>
                                                		<form action="<%= request.getContextPath() %>/ServletTelefone" method="post" id="formFone">
                                                			<div class="form-group row">
                                                               	<div class="col-sm-6">
                                                               		<label class="col-form-label">ID Usuario</label>
                                                                   	<input type="text" name="id" id="id" class="form-control" readonly="readonly" value="${ login.id }">
                                                                </div>
                                                               	<div class="col-sm-6">
                                                              		<label class="col-form-label">Nome</label>
                                                                  	<input readonly="readonly" type="text" name="nome" id="nome" class="form-control" placeholder="Nome"  required="required" value="${ login.nome }">
                                                                </div>
                                                                <div class="col-sm-6">
                                                              		<label class="col-form-label">Numero</label>
                                                                  	<input type="text" name="numero" id="numero" class="form-control" placeholder="Numero"  required="required">
                                                                </div>
                                                        	</div>
                                                        	<button class="btn btn-success waves-effect waves-light">Salvar</button>
                                                		</form>
                                                	</div>
                                            	</div>
                                        	</div>
                                    	</div>
                                    	<span id="msg">${ msg }</span>
                                    	<div style="height: 300px; overflow: scroll; overflow-x: hidden;">
											<table class="table" id="tabelaResultadosView">
												<thead>
													<tr>
														<th style="text-align: center;" scope="col">ID</th>
														<th style="text-align: center;" scope="col">Numero</th>
														<th style="text-align: center;" scope="col">Excluir</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${ modelTelefones }" var="mf">
														<tr>
														<td style="text-align:center;"><c:out value="${ mf.id }"></c:out></td>
														<td style="text-align:center;"><c:out value="${ mf.numero }"></c:out></td>
														<td style="text-align:center;"><a class="btn btn-danger" href="<%=request.getContextPath() %>/ServletTelefone?acao=excluir&id=${mf.id }&userpai=${login.id}">Excluir</a></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
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
<script>

$("#numero").keypress(function (event) {
	return /\d/.test(String.fromCharCode(event.keyCode));
});

</script>
</body>

</html>
