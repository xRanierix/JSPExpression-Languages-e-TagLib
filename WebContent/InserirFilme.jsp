<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Filme</title>
</head>
<body>
<h1>Novo Filme</h1>
	<p> Digite os dados do filme para cadastrar</p>
	<form action="manter_filmes.do" method="POST">
		<p><label>Tí­tulo do Filme: </label><input type="text" name="titulo" required/></p>
		<p><label>Descrição: </label><input type="text" name="descricao" /></p>
		<p><label>Diretor: </label><input type="text" name="diretor"/>
		
		<label>Gênero: </label><select name="genero" required>
		<c:forEach var="genero" items="${generos}">
			<option value="${genero.id}">${genero.nome}</option>
		</c:forEach>
		</select>
		</p>
		
		<p><label>Lançamento: </label><input type="date" name="data_lancamento"/>
		<label>Popularidade: </label><input type="number" name="popularidade"/></p>
		<p><label>URL do Pôster: </label><input type="text" name="poster_path"/></p>
		<p><input type="submit" name="acao" value="inserir"></p>
	</form>
</body>
</html>