<%@page import="pojos.Funcionario"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="pojos.Setor"%>
<%@page import="java.util.List"%>
<%@page import="dao.GenericDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tudo sobre Funcionario</title>
</head>
<body>


	<%request.getSession().setAttribute("pagina", "cad_funcionario"); %>

	<!-- Listar Funcionarios -->
	
	<a href="index.jsp">Voltar</a>

	<h2 align="center">Funcionarios Cadastrados</h2>

	<table align="center" border="1">
		<tr>
			<td align="center">Codigo</td>
			<td align="center">Nome do Funcionário</td>
			<td align="center">Setor de Lotação</td>
		</tr>

		<%
			GenericDao conn = new GenericDao();
			List<Funcionario> funcionarios = conn.buscarTodosFuncionarios();

			for (Funcionario funcionario : funcionarios) {
		%>
		<tr>
			<td align="center"><%=funcionario.getId()%></td>
			<td align="center"><%=funcionario.getNome()%></td>
			<td align="center"><%=funcionario.getSetor().getId()%></td>
		</tr>
		<%
			}
		%>

	</table>

	<!-- Cadastrar Funcionarios -->
	<h2>Novo Registro</h2>

	<form action="empresaServlet" method="post">
		<table>
			<tr>
				<td>Codigo: <input type="text" name="cd_funcionario" />
				</td>
			</tr>
			<tr>
				<td>Nome: <input type="text" name="nome" /></td>
			</tr>
			<tr>
				<td>Setor: <select name="cd_setor">
						<% 
				List<Setor> setores = conn.buscarTodosSetores();
				for(Setor setor : setores){%>
						<option  value="<%=setor.getId()%>"><%=setor.getNome()%></option>
						<%} %>
				</select></td>
			</tr>
			<tr>
				<td><input type="submit" name="btn_cad" value="Enviar" />
				</td>
				<td><input type="reset" name="btn_limpar" value="Limpar" />
				</td>
			</tr>
		</table>
		<input type="hidden" name="cadastrar" value="cadastrar" />
	</form>

	<br>
	<br>
	<!-- Excluir Setores -->
	<h2>Excluir Registro</h2>
	<form action="empresaServlet" method="post">
		<table>
			<tr>
				<td>Codigo: <input type="text" name="cd_funcionario" /></td>
				<td><input type="submit" name="btn_excluir" value="Excluir" />
				</td>
			</tr>
		</table>
		<input type="hidden" name="excluir" value="excluir" />
	</form>

</body>
</html>