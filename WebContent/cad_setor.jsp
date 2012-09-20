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
<title>Tudo sobre SETOR</title>
</head>
<body>

<%request.setAttribute("pagina", "cad_setor"); %>

	<!-- Listar Setores -->

	<h2 align="center">Setores Cadastrados</h2>

	<table align="center" border="1">
		<tr>
			<td align="center">Codigo</td>
			<td align="center">Descrição do Setor</td>
		</tr>

		<%
			GenericDao conn = new GenericDao();
			List<Setor> setores = conn.buscarTodosSetores();

			for (Setor setor : setores) {
		%>
		<tr>
			<td align="center"><%=setor.getId()%></td>
			<td align="center"><%=setor.getNome()%></td>
		</tr>
		<%
			}
		%>

	</table>

	<!-- Cadastrar Setor -->
	<h2>Novo Registro</h2>

	<form action="empresaServlet" method="post">
		<table>
			<tr>
				<td>Codigo: <input type="text" name="cd_setor" /></td>
			</tr>
			<tr>
				<td>Nome: <input type="text" name="nome" /> </td>
			</tr>
			<tr>
				<td><input type="submit" name="btn_cad" value="Enviar" /></td>
				<td><input type="reset" name="btn_limpar" value="Limpar" /></td>
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
				<td>Codigo: <input type="text" name="cd_setor" />
				</td>
				<td><input type="submit" name="btn_excluir" value="Excluir" />
				</td>
			</tr>
		</table>
		<input type="hidden" name="excluir" value="excluir" />
	</form>

</body>
</html>