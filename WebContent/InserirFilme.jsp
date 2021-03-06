<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastrar Filme</title>
<!-- Font-awesome - ícones -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous" />
        
<!-- Fonte do Google -->
<link href="https://fonts.googleapis.com/css?family=Luckiest+Guy|Source+Sans+Pro" rel="stylesheet" />

</head>
<body>
<h1>Novo Filme</h1>
	<p> Digite os dados do filme para cadastrar</p>
	<form action="manter_filmes.do" method="POST">
		<p><label>Título do Filme: </label><input type="text" name="titulo" required/></p> <!-- criando var do html para serem referenciadas no inserir do controller -->
		<p><label>Descrição: </label><input type="text" name="descricao" /></p>
		<p><label>Diretor: </label><input type="text" name="diretor"/>
		
		<label>Gênero: </label><select name="genero" required>
		<c:forEach var="genero" items="${generos}"> <!-- o generos está referenciando a array que o controller me passou -->
			<option value="${genero.id}">${genero.nome}</option> <!-- este genero está referenciando cada item da array -->
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